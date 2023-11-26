# SC2002_Group5-V2

### Compile code from the root directory
```
javac Camp/*.java Comment/*.java Controller/*.java Menu/*.java Program/*.java Users/*.java Menu/CampHandlers/*.java Menu/CommentHandler/*.java
```


### TestCase.py
1. Ensure that the Java program (MainProgram) is correctly compiled and accessible.
2. Prepare a file named Suggested_Inputs_For_Program_Testing.txt with the desired input scenarios.
3. Run the Python script, and it will automate the execution of the Java program with the provided inputs.


  ### Suggested_Inputs_For_Program_Testing.txt
#### Staff A Workflow

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

5. **Raise Enquiry and Suggestion:**
   - Join NTU as Community Leader.
   - Join SCSE as Attendee but date clashes.
   - Raise an enquiry to SCSE - requesting a date change.
   - Raise an enquiry to NTU - FAIL (own camp).
   - Raise a suggestion to NTU - proposing to move the camp back a day.
   - Raise a 2nd suggestion to NTU - request halal food.
   - Edit the 2nd suggestion to NTU - rephrase the request politely for halal food.
   - Delete the halal suggestion (remembering staff assured halal food).
   - Click slide.

6. **Staff A Actions:**
   - View suggestion to NTU.
   - Accept suggestion (extend NTU camp by a day).
   - Edit NTU camp date.
   - View enquiry to SCSE.
   - Reply NO.
   - Click slide.

#### Staff B Workflow

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
   - Click slide.

#### Student B Workflow

10. **Joining and Enquiry:**
    - Join SCSE as a Community Leader (make comm full).
    - Join NTU as an Attendee.
    - Raise an enquiry to NTU - inquire about halal food?

#### Student C Workflow

11. **Joining and Quitting:**
    - Join SCSE as a Community Leader but full, so join SCSE as an Attendee.
    - Join COE and quit.
    - Click slide.
    - Join NTU as a Community Leader + reply.
    - Generate a report.

#### Staff Actions

12. **Staff Operations:**
    - Generate NTU, SCSE report.
    - Delete COE.
    - If no time, delete the camp done by Staff A.

#### Note
   - "Total Slots:Comm Slots" format indicates the camp's capacity and the number of slots allocated for Community Leaders.
   - Each action corresponds to a specific user's or staff member's interactions with the camp system.
