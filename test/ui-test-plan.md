# Nori UI Test Plan

Decorative blank, banner, and divider lines are omitted from expected output below. All other listed lines must appear in the given order.

## TC1 - Manage all task types

Aim: Verify creation and listing of todos, deadlines, and events.

Inputs:

```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
list
bye
```

Expected output:

```text
Hello! I'm Nori.
What can I do for you?
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 task in the list.
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
Bye. Hope to see you again soon!
```

## TC2 - Update completion state

Aim: Verify that mark and unmark update the selected task.

Inputs:

```text
todo read book
mark 1
unmark 1
bye
```

Expected output:

```text
Hello! I'm Nori.
What can I do for you?
Got it. I've added this task:
  [T][ ] read book
Now you have 1 task in the list.
Nice! I've marked this task as done:
  [T][X] read book
OK, I've marked this task as not done yet:
  [T][ ] read book
Bye. Hope to see you again soon!
```
