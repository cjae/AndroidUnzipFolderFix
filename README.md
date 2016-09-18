# AndroidUnzipFolderFix
Android (java) code that successfully unzips a folder in internal storage

I needed to unzip a folder I downloaded from a server, but all the unzip code I saw had one issue or the other.
I got a code which successfully unzips a zipped folder containing just files but throws an error when I tried using it to unzip a zipped folder containing a folder(s).
So I had to create a fix.
This is the decompress(unzip) code snippet i came up with.
