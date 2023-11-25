import subprocess
import os
import time

file_to_delete = 'mainObj.ser'

if os.path.exists(file_to_delete):
    os.remove(file_to_delete)
else:
    print("The file does not exist and cannot be deleted.")

java_program_path = 'Program.MainProgram'

input_file_path = 'Suggested_Inputs_For_Program_Testing.txt'

with open(input_file_path, 'r') as file:
    inputs = [line.strip() for line in file.readlines() if not line.strip().startswith('//')]

process = subprocess.Popen(['java', java_program_path], stdin=subprocess.PIPE, text=True)
prev = ""
for line in inputs:
    process.stdin.write(line + '\n')
    # time.sleep(1)
    process.stdin.flush()

process.stdin.close()
process.wait()
