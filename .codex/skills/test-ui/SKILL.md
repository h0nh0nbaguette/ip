---
name: test-ui
description: Run repeatable command-line UI tests for Nori after Java source or user-visible behavior changes. Use for the CS2103 iP console application; do not use for GUI testing.
---

# Test Nori's command-line UI

Read `test/ui-test-plan.md` before testing. If behavior changed, update the plan first so each affected case still states its aim, inputs, and expected output.

1. Verify that JDK 25 is selected.
2. Compile every `src/main/java/*.java` file into a temporary directory outside the repository. Never leave `.class` files under `src/`.
3. Run the requested cases, or all cases in `test/ui-test-plan.md` when none are specified. Feed the listed commands to standard input in order.
4. Normalize CRLF/LF differences, then compare the actual semantic output with the expected ordered lines. Decorative banner, blank, and divider lines may be ignored only when the test case says so.
5. Stop at the first mismatch. Report the case, inputs, expected output, and complete console transcript. On success, report every case that passed and include the console transcript so the session can be reviewed.

Do not change application code merely to make an outdated test pass. Reconcile the test with the current requirement first.
