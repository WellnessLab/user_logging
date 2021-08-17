# User Logging - Wellness Technology Lab
An library to log user actions. Designed for user research purposes.

## Example
```java
WellnessUserLogging userLogging = new WellnessUserLogging("user_id_xxxx");

Bundle bundle = new Bundle();
bundle.putString("STORY_ID", "Story ABC");
bundle.putInt("PAGE_ID", 8);
bundle.putFloat("AVERAGE", 0.5);

userLogging.logEvent("FORM_SUBMITTED", bundle);
```
