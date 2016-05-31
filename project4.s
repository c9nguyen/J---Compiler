# .\project4.s
# Source file: project4.java
# Compiled: Mon May 30 20:24:39 PDT 2016

.text

main:
    subu    $sp,$sp,76 	 # Stack frame is 76 bytes long
    sw      $ra,72($sp) 	 # Save return address
    sw      $fp,68($sp) 	 # Save frame pointer
    sw      $t0,64($sp) 	 # Save register $t0
    sw      $t0,60($sp) 	 # Save register $t0
    sw      $t0,56($sp) 	 # Save register $t0
    sw      $t1,52($sp) 	 # Save register $t1
    sw      $t2,48($sp) 	 # Save register $t2
    sw      $t0,44($sp) 	 # Save register $t0
    sw      $t0,40($sp) 	 # Save register $t0
    sw      $t3,36($sp) 	 # Save register $t3
    sw      $t0,32($sp) 	 # Save register $t0
    sw      $t3,28($sp) 	 # Save register $t3
    sw      $t2,24($sp) 	 # Save register $t2
    sw      $t0,20($sp) 	 # Save register $t0
    sw      $t4,16($sp) 	 # Save register $t4
    sw      $t0,12($sp) 	 # Save register $t0
    sw      $t3,8($sp) 	 # Save register $t3
    sw      $t0,4($sp) 	 # Save register $t0
    sw      $t0,0($sp) 	 # Save register $t0
    addiu   $fp,$sp,72 	 # Save frame pointer

main.0:

main.1:
    li $t0,4
    move $t2,$t0

main.2:
    li $t0,6
    bgt $t2,$t0,main.4
    j main.3

main.3:
    li $t0,-1
    add $t3,$t2,$t0
    move $t2,$t3
    j main.2

main.4:
    li $t0,0
    move $t3,$t2
    move $t2,$t0

main.5:
    bgt $t2,$t3,main.7
    j main.6

main.6:
    li $t0,-1
    add $t4,$t3,$t0
    li $t0,-1
    add $t3,$t2,$t0
    move $t2,$t3
    move $t3,$t4
    j main.5

main.7:
    li $t0,5
    bgt $t3,$t0,main.9
    j main.8

main.8:
    li $t0,1
    j main.10

main.9:
    li $t1,0

main.10:
    li $t0,6
    add $t0,$t1,$t0
    j main.restore

main.restore:
    lw      $ra,72($sp) 	 # Restore return address
    lw      $fp,68($sp) 	 # Restore frame pointer
    lw      $t0,64($sp) 	 # Restore register $t0
    lw      $t0,60($sp) 	 # Restore register $t0
    lw      $t0,56($sp) 	 # Restore register $t0
    lw      $t1,52($sp) 	 # Restore register $t1
    lw      $t2,48($sp) 	 # Restore register $t2
    lw      $t0,44($sp) 	 # Restore register $t0
    lw      $t0,40($sp) 	 # Restore register $t0
    lw      $t3,36($sp) 	 # Restore register $t3
    lw      $t0,32($sp) 	 # Restore register $t0
    lw      $t3,28($sp) 	 # Restore register $t3
    lw      $t2,24($sp) 	 # Restore register $t2
    lw      $t0,20($sp) 	 # Restore register $t0
    lw      $t4,16($sp) 	 # Restore register $t4
    lw      $t0,12($sp) 	 # Restore register $t0
    lw      $t3,8($sp) 	 # Restore register $t3
    lw      $t0,4($sp) 	 # Restore register $t0
    lw      $t0,0($sp) 	 # Restore register $t0
    addiu   $sp,$sp,76 	 # Pop stack
    jr      $ra 	 # Return to caller



# SPIM Runtime

# Copyright 2013 Bill Campbell, Swami Iyer and Bahar Akbal-Delibas

# SPIM.s 

.text

# Print the integer value passed as parameter.

spim.SPIM.printInt:

    subu $sp,$sp,32     # Stack frame is 32 bytes long
    sw $fp,28($sp)      # Save frame pointer
    addu $fp,$sp,3      # Set up frame pointer

    li $v0,1            # Syscall code to print an integer
    syscall	        # Prints the arg value

    lw $fp,28($sp)      # Restore frame pointer
    addiu $sp,$sp,32    # Restore the stack pointer
    jr $ra              # Return to caller

# Print the float value passed as parameter.

spim.SPIM.printFloat:

    subu $sp,$sp,32     # Stack frame is 32 bytes long
    sw $fp,28($sp)      # Save frame pointer
    addu $fp,$sp,32     # Set up frame pointer

    li $v0,2            # Syscall code to print a float
    syscall             # Prints the arg value

    lw $fp,28($sp)      # Restore frame pointer
    addiu $sp,$sp,32    # Restore the stack pointer
    jr $ra              # Return to caller

# Print the double value passed as parameter.
	
spim.SPIM.printDouble:
    subu $sp,$sp,32    # Stack frame is 32 bytes long
    sw $fp,28($sp)     # Save frame pointer
    addu $fp,$sp,32    # Set up frame pointer

    li $v0,3           # Syscall code to print a double
    syscall            # Prints the arg value

    lw $fp,28($sp)     # Restore frame pointer
    addiu $sp,$sp,32   # restore the stack pointer
    jr $ra             # Return to caller

# Print the string value passed as parameter.

spim.SPIM.printString:
    subu $sp,$sp,32    # Stack frame is 32 bytes long
    sw $fp,28($sp)     # Save frame pointer
    addu $fp,$sp,32    # Set up frame pointer

    li $v0,4           # Syscall code to print a string
    syscall            # Print the string value

    lw $fp,28($sp)     # Restore frame pointer
    addiu $sp,$sp,32   # Restore the stack pointer
    jr $ra             # Return to caller

# Print the char value passed as parameter.

spim.SPIM.printChar:
	subu $sp,$sp,32	   # Stack frame is 32 bytes long
	sw $fp,28($sp)     # Save frame pointer
	addu $fp,$sp,32    # Set up frame pointer


	li $v0,11          # Syscall code to print a char
	syscall            # Print the char value

	lw $fp,28($sp)     # Restore frame pointer
	addiu $sp,$sp,32   # Restore the stack pointer
	jr $ra             # Return to caller

# Read the integer value from the user through console.

spim.SPIM.readInt:
	subu $sp,$sp,32	        # Stack frame is 32 bytes long
	sw $fp,28($sp)          # Save frame pointer
	addu $fp,$sp,32         # Set up frame pointer

	li $v0,5                # Syscall code to read an integer
	syscall                 # Load the integer value read from console into $v0

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32        # Restore the stack pointer
	jr $ra                  # Return to caller

# Read the float value from the user through console.
	
spim.SPIM.readFloat:
	subu $sp,$sp,32	        # Stack frame is 32 bytes long
	sw $fp,28($sp)          # Save frame pointer
	addu $fp,$sp,32	        # Set up frame pointer

	li $v0,6                # Syscall code to read a float
	syscall	                # Load the float value read from console into $f0

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32        # Restore the stack pointer
	jr $ra                  # Return to caller

# Read the double value from the user through console.

spim.SPIM.readDouble:
	subu $sp,$sp,32	        # Stack frame is 32 bytes long
	sw $fp,28($sp)          # Save frame pointer
	addu $fp,$sp,32	        # Set up frame pointer

	li $v0,7                # Syscall code to read a double
	syscall	                # Load the float value read from console into $f0

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32        # Restore the stack pointer
	jr $ra                  # Return to caller
   
# Read the string value from the user through console.

spim.SPIM.readString:
	subu $sp,$sp,32		# Stack frame is 32 bytes long
	sw $fp,28($sp)		# Save frame pointer
	addu $fp,$sp,32		# Set up frame pointer

	li $v0,8		# Syscall code to read a string
	syscall			# Load the string value; $a0 = buffer, $a1 = length

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32	# Restore the stack pointer
	jr $ra 			# Return to caller

# Read the char value from the user through console.

spim.SPIM.readChar:
	subu $sp,$sp,32	        # Stack frame is 32 bytes long
	sw $fp,28($sp)          # Save frame pointer
	addu $fp,$sp,32	        # Set up frame pointer

	li $v0,12               # Syscall code to read a char
	syscall	                # Load the char value read from console into $a0

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32        # Restore the stack pointer
	jr $ra                  # Return to caller
	
# Opens a file. This operation uses two arguments:
# $a0 = the address of the file name to open
# $a1 = flags  (0: read only, 1: write only, 2: read and write, 
#               100: create file, 8: append data)
# These registers are assumed to be set by the caller before calling this procedure

spim.SPIM.open:
	subu $sp,$sp,32         # Stack frame is 32 bytes long
	sw $fp,28($sp)          # Save frame pointer
	addu $fp,$sp,32         # Set up frame pointer

	li $v0,13       	# System call code for open file
  	syscall          	# Open a file (file descriptor returned in $v0)

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32        # Restore the stack pointer
	jr $ra                  # Return to caller

# Reads from a file. This operation uses three arguments:
# $a0 = file descriptor
# $a1 = the address of input buffer
# $a2 = the length of bytes to read
# These registers are assumed to be set by the caller before calling this procedure

spim.SPIM.read:
	subu $sp,$sp,32         # Stack frame is 32 bytes long
	sw $fp,28($sp)          # Save frame pointer
	addu $fp,$sp,32         # Set up frame pointer

	li $v0,14               # System call code for read file
  	syscall                 # Read from file ($a0 contains file descriptor, $v0 contains 
  	                        # number of character read)

	lw $fp,28($sp)          # Restore frame pointer
	addiu $sp,$sp,32        # Restore the stack pointer
	jr $ra                  # Return to caller

# Writes to a file. This operation uses three arguments:
# $a0 = file descriptor
# $a1 = the address of output buffer
# $a2 = the length of bytes to write
# These registers are assumed to be set by the caller before calling this procedure

spim.SPIM.write:
	subu $sp,$sp,32     # Stack frame is 32 bytes long
	sw $fp,28($sp)      # Save frame pointer
	addu $fp,$sp,32     # Set up frame pointer

 	li $v0,15           # System call for write to file
  	syscall             # Write to file  ($a0 contains file descriptor)

	lw $fp,28($sp)      # Restore frame pointer
	addiu $sp,$sp,32    # Restore the stack pointer
	jr $ra              # Return to caller

# Close a file ($a0 contains file descriptor).

spim.SPIM.close:
	subu $sp,$sp,32	    # Stack frame is 32 bytes long
	sw $fp,28($sp)      # Save frame pointer
	addu $fp,$sp,32     # Set up frame pointer

	li $v0,16           # System call code for close
  	syscall		    # Close file

	lw $fp,28($sp) 	    # Restore frame pointer
	addiu $sp,$sp,32    # Restore the stack pointer
	jr $ra              # Return to caller

# Exit SPIM.
	
spim.SPIM.exit:
    li $v0,10        # Syscall code to exit
    syscall

# Exit SPIM with a specified code (in $a0).
	
spim.SPIM.exit2:
    li $v0,17        # Syscall code to exit2
    syscall
	
