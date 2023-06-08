# question
A module for GameDev


## Functional
- Question is a simple **text**.
- Answer can be **True** or **False**.
- If answer is **correct** **user** earn 5 **points**. **Incorrect** 0 point.

## Data representation
- User : basic user informations
    - login
    - name
    - prefrences
- Question : all aviallable questions
    - content
- Answer : correct answer for questions
    - correctAnswer
- UserAnswer : question asked to a specific user. Once answered contains points earn by user.
    - points
- Tag and Category : A tag is a simple text. Each tag have ONE Category. A User can declare his point of interest. Question are linked to tags too.
       