# SC2002_Group5-V2

## Overview
This README provides instructions for compiling and testing the Java program for SC2002 Group5, a project developed as part of the SC2002 Object-Oriented Design & Programming course. The project involves the development of the Camp Application and Management System (CAMS).

## Compilation Instructions
To compile the code, navigate to the root directory and execute the following commands in the terminal:

```bash
javac Camp/*.java Comment/*.java Controller/*.java Menu/*.java Program/*.java Users/*.java Menu/CampHandlers/*.java Menu/CommentHandler/*.java Menu/CommentHandler/Interfaces/*.java
```

This command compiles all the Java files in the specified directories.

## Testing Instructions
### Preparing for Test
1. Ensure that the Java program (MainProgram) is correctly compiled and accessible.
2. Prepare a file named `Suggested_Inputs_For_Program_Testing.txt` with the desired input scenarios.
### Manually test
1. Ensure all the files are correctly compiled.
2.
```bash
java Program/MainProgram.java
```
3. login as admin to add users - username: admin, password: password
4. Refer to `Suggested_Inputs_For_Program_Testing.txt` 

### Running the Test
3. Run the following Python script to automate the execution of the Java program with the prepared inputs:

```bash
python TestCase.py
```

This script `TestCase.py` reads inputs from `Suggested_Inputs_For_Program_Testing.txt` and runs the Java program for each test case.

### [Test Cases](Suggested_Inputs_For_Program_Testing.txt)

#### Staff A (ourin) 

1. **Create NTU and SCSE but dates clash:**
   - Create NTU (Total Slots: 10, Comm Slots: 4) - date error.
   - Create SCSE (Total Slots: 3, Comm Slots: 1) - date error, clashes with NTU.

2. **View Created Camps:**
   - View the created camps.

3. **Change Password:**
   - Create a password.
   - Try the same previous password.
   - Change the password again, but the new password does not match.

4. **Camp Modification:**
   - View the created camps.
   - Edit NTU camp date.
   - View the created camps.

#### Student A (ychern) action 
5. **Raise Enquiry and Suggestion:**
   - Join NTU as committee member.
   - Join SCSE as Attendee but date clashes.
   - Raise an enquiry to SCSE - requesting a date change.
   - Raise an enquiry to NTU - FAIL (own camp).
   - Raise a suggestion to NTU - proposing to move the camp back a day.
   - Raise a 2nd suggestion to NTU - request halal food.
   - Edit the 2nd suggestion to NTU - rephrase the request politely for halal food.
   - Delete the halal suggestion (remembering staff assured halal food).


6. **Staff A Actions:**
   - View suggestion to NTU.
   - Accept suggestion (extend NTU camp by a day).
   - Edit NTU camp date.
   - View enquiry to SCSE.
   - Reply NO.
   - Try to delete NTU Ori but fail because already have students joined.

#### Staff B (upam) Workflow

7. **Create COE and Visibility:**
   - Create COE (Total Slots: 20, Comm Slots: 9) but named as SCSE - exceeds max comm - invisible.
   - View COE filter by location.

8. **Make COE Visible:**
   - Make COE visible.
   - Click slide.

9. **Student A Actions:**
   - View all camps - surprised to see COE as it is now visible.
   - View suggestion to NTU.
   - View enquiry to SCSE.
   - Delete enquiry but FAIL (already processed).
   - Join SCSE as an Attendee.

#### Student B (ct113) Workflow

10. **Joining and Enquiry:**
    - Join SCSE as a committee member (make comm full).
    - Join NTU as an Attendee.
    - Raise an enquiry to NTU - enquire about halal food

#### Student C (dl007) Workflow

11. **Joining and Quitting:**
    - Join SCSE as a committee member but full, so join SCSE as an Attendee.
    - Join COE and withdraw, then join again but fail.
    - Join NTU as a committee member + reply.
    - Generate a report.

#### Staff Actions

12. **Staff Operations:**
    - Staff A Generate NTU report and delete NTU (but FAIL because already have students joined).
    - Staff B delete COE (SUCCESS because no one joining).

#### Note
   - "Total Slots:Comm Slots" format indicates the camp's capacity and the number of slots allocated for Community Leaders.
   - Each action corresponds to a specific user's or staff member's interactions with the camp system.
