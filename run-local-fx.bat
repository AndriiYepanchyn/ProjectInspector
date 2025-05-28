
ECHO Ensure you've set env variable JAVAFX_PATH (21 version required) : %JAVAFX_PATH%
java --module-path %JAVAFX_PATH% --add-modules javafx.controls,javafx.fxml -jar target/projectinspector-1.0-SNAPSHOT.jar