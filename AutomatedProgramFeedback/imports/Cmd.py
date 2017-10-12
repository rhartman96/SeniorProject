import subprocess
import os
import signal
from threading import Timer

def runCmd(cmd, verbose=False, workingDir=None, timeout=None):
	## call date command ##
	p = None
	if(workingDir):
		p = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True, cwd=workingDir, preexec_fn=os.setsid)
	else:
		p = subprocess.Popen(cmd, stdout=subprocess.PIPE, stderr=subprocess.PIPE, shell=True, preexec_fn=os.setsid)
	

	output = None
	err = None
	p_status = -1
	if(timeout):
		(output, err) = runWithTimeout(p, timeout)
	else:
		#Interact with process: Send data to stdin. Read data from stdout and stderr, until end-of-file is reached. Wait for process to terminate. The optional input argument should be a string to be sent to the child process, or None, if no data should be sent to the child.
		(output, err) = p.communicate()
	
	## Wait for cmd to terminate. Get return returncode ##
	p_status = p.wait()


	if(output):
		output = output.decode("utf-8")
	if(err):
		err = err.decode("utf-8")

	if(verbose):
		if(output):
			print("Command output: " + str(output), flush=True)
		if(err):
			print("Command error: " + str(err), flush=True)
			print("Command: " + str(cmd), flush=True)
			print("Working Dir: " + str(workingDir), flush=True)
		
		print("Command exit status/return code: " + str(p_status), flush=True)

	return (str(output), str(err), p_status)


def runWithTimeout(proc, timeout_sec):
  kill_proc = lambda p: killProc(p)
  timer = Timer(timeout_sec, kill_proc, [proc])
  output = None
  err = None
  try:
    timer.start()
    (output, err) = proc.communicate()
  finally:
    timer.cancel()

  return (output, err)

def killProc(proc):
	os.killpg(os.getpgid(proc.pid), signal.SIGTERM)  # Send the signal to all the process groups
