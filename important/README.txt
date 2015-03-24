For google maps to work properly, we all need to be developing the app using the same SHA-1 Fingerprint.
What that is / means isn't really important. What IS important as that you do the following steps to make
sure you are using the same key as everyone else, so Google Maps works.

1. Navigate to the directory containing your keys. On Windows Vista and and 7 it is:
    C:\Users\%YOURUSERNAME%\.android\

    For other OS's you will have to do a bit of googling to find the correct directory.
    The file of interest is debug.keystore


2. (OPTIONAL, but recommended) Change the file 'debug.keystore' to 'debug_backup.keystore'
    If anything breaks / gets messed up, you can always revert this back to 'debug.keystore'
    to undo any changes you do in step 3.

3. Copy the 'debug.keystore' file inside the repository directory named 'important' into
    your keystore directory. If you didn't change the file-name of your current keystore
    (step 2), then this will overwrite it.