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

## TC3 - Recover from invalid commands

Aim: Verify that invalid commands produce specific errors without changing the task list or stopping Nori.

Inputs (the first input is a blank line):

```text

todo
deadline submit report
deadline /by Friday
event meeting /to 4pm /from 2pm
event meeting /from 2pm /to
unknown command
mark
mark one
mark 1
todo valid task
mark 2
list
bye
```

Expected output:

```text
Hello! I'm Nori.
What can I do for you?
OOPS!!! Please enter a command.
OOPS!!! The description of a todo cannot be empty.
OOPS!!! Use: deadline DESCRIPTION /by DATE_OR_TIME
OOPS!!! A deadline needs both a description and /by value.
OOPS!!! Use: event DESCRIPTION /from START /to END
OOPS!!! Use: event DESCRIPTION /from START /to END
OOPS!!! I don't know that command.
OOPS!!! Please provide a task number after mark.
OOPS!!! The task number must be a whole number.
OOPS!!! That task number is not in your list.
Got it. I've added this task:
  [T][ ] valid task
Now you have 1 task in the list.
OOPS!!! That task number is not in your list.
Here are the tasks in your list:
1.[T][ ] valid task
Bye. Hope to see you again soon!
```
