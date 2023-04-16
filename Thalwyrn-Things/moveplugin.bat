@REM Delete the old file
del "C:\Users\Wild\Minecraft Testing Server\plugins\thalwyrnThings*.jar"

@REM Move the file thalwyrnThings* to C:\Users\Wild\Minecraft Testing Server\plugins and overwrite the existing file
move /y ".\target\thalwyrnThings*.jar" "C:\Users\Wild\Minecraft Testing Server\plugins\"