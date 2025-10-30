# AngelScript IntelliJ Plugin – Implementation Plan

## Current Status

- LSP (lsp4ij) runs server; .as files recognized; basic lexer highlighting works.
- Semantic token color provider implemented; file icons present.
- Inline diagnostics working (errors underline and tooltip visible).
- Custom `angelscript/wantSave` notification handled.
- Inlay hints, Code Lens, API Browser, Settings UI, Debugging not yet implemented/verified.

---

## Priorities

- Medium: Inlay hints
    - Ensure LSP capability negotiation enables inlay hints
    - Verify rendering via lsp4ij
    - Plumb configuration (enable/disable categories) to `workspace/didChangeConfiguration`

- Medium: Code Lens
    - Verify server’s code lens provider is active
    - Wire IntelliJ command handling for blueprint actions (open/create)
    - Confirm visibility on eligible classes

- Medium: API Browser tool window
    - Tool window with search/list/details
    - Backed by custom requests: `angelscript/getAPI`, `angelscript/getAPISearch`, `angelscript/getAPIDetails`

- Low: Handle additional custom LSP notifications
    - support `angelscript/wantSave`
    - Add flexible handler for future angelscript-* notifications if needed

- Low: Settings UI for server + feature toggles
    - Unreal host/port, diagnostics preferences
    - Completion, inlay hints, code lens toggles
    - Apply → restart LSP server and send `workspace/didChangeConfiguration`

- Low: Debugging support (DAP)
    - Future bridge to Unreal Angelscript debug adapter
    - Breakpoints, stack, variables, inline values

---

## Tasks and Notes

### Inlay Hints (Medium)

- Confirm server `inlayHintProvider` capability is announced
- Verify hints render in the editor
- Expose toggles and plumb to server settings

Acceptance:

- Parameter and type hints appear, respond to toggles.
- Integration test opens sample file and asserts inlay hints present/absent per settings.

### Code Lens (Medium)

- Ensure code lens requests reach the server
- Add action wiring for blueprint open/create

Acceptance:

- Lenses visible above eligible classes; actions execute or display meaningful feedback.
- Integration test verifies lenses rendered and that command handler receives invocation (mocked action execution).

### API Browser Tool Window (Medium)

- Add tool window with search, list, details
- Use `angelscript/getAPI*` requests; handle async load until Unreal types are ready

Acceptance:

- Browsable API tree; details show signatures/doc.
- Integration test opens tool window, triggers search, and validates mocked server results appear.

### Custom LSP Notifications (Low)

- General handler for future angelscript notifications

Acceptance:

- No-ops safely on unknown messages; `wantSave` continues to work.
- Integration test sends `angelscript/wantSave` and asserts `saveAllDocuments` is invoked.

### Settings UI (Low)

- Persistent model and UI for host/port and feature toggles
- Restart server/apply settings; send `didChangeConfiguration`

Acceptance:

- Settings persist and affect behavior without IDE restart, barring server restart when needed.
- Integration test updates settings, verifies persisted state and that server receives configuration change or restarts.

### Debugging (Low)

- DAP bridge to Node debug adapter

Acceptance:

- Deferred; documented approach and risks.

---

## Technical Plan Details

### Configuration → Server

- Send `workspace/didChangeConfiguration` to update server settings (diagnostics, completion, inlay hints, code lenses,
  etc.)

### Testing Strategy
 
- Manual: verify diagnostics, status icon, inlay hints, lenses, API browser
- Integration:
  - Status indicator transitions with mocked diagnostics and process lifecycle
  - Inlay hints appear/disappear per settings on a sample `.as` file
  - Code lenses render and invoke a mocked action handler
  - API browser loads mocked API payloads and updates UI on search
  - `angelscript/wantSave` triggers save-all behavior
  - Settings changes persist and are propagated to server (restart or configuration change)
- Automated unit tests: keep current unit tests; add lightweight checks where feasible

---

## Next Actions

1. ~~Fix Unreal connection indicator.~~
2. Enable and verify Inlay Hints.
3. Enable and verify Code Lens + actions.
4. Implement API Browser tool window.
5. Add Settings UI for host/port and toggles.
6. Extend custom notification handling if needed.
7. Plan DAP integration (future).
