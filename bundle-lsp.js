const esbuild = require('esbuild');
const path = require('path');
const fs = require('fs');

async function bundle() {
  try {
    // First, bundle the server
    await esbuild.build({
      entryPoints: [path.join(__dirname, 'unreal-angelscript-lsp', 'language-server', 'out', 'server.js')],
      bundle: true,
      outfile: path.join(__dirname, 'src', 'main', 'resources', 'js', 'angelscript-language-server.js'),
      platform: 'node',
      target: 'node14',
      format: 'cjs',
      external: [],
      minify: false,
      sourcemap: false,
      logLevel: 'info',
    });

    // Patch the bundled file to use stdin/stdout instead of IPC
    const bundledPath = path.join(__dirname, 'src', 'main', 'resources', 'js', 'angelscript-language-server.js');
    let content = fs.readFileSync(bundledPath, 'utf8');
    
    // Replace the createConnection call to use stdin/stdout
    // The bundled code uses (0, node_1.createConnection) pattern
    content = content.replace(
      /node_1\.IPCMessageReader\(process\)/g,
      'node_1.StreamMessageReader(process.stdin)'
    );
    content = content.replace(
      /node_1\.IPCMessageWriter\(process\)/g,
      'node_1.StreamMessageWriter(process.stdout)'
    );
    
    // Also handle non-namespaced version just in case
    content = content.replace(
      /IPCMessageReader\(process\)/g,
      'StreamMessageReader(process.stdin)'
    );
    content = content.replace(
      /IPCMessageWriter\(process\)/g,
      'StreamMessageWriter(process.stdout)'
    );
    
    fs.writeFileSync(bundledPath, content);
    console.log('✓ Language server bundled and patched for stdio communication!');
  } catch (error) {
    console.error('✗ Bundling failed:', error);
    process.exit(1);
  }
}

bundle();
