from tkinter import *
from tkinter.font import Font 
# Importing tkinter library 

root = Tk()
root.configure(background = "#A020f0")
root.title("To-Do List App")
root.geometry("750x500")
# Here I am doing the following: 
# First I created a root window. 
# Next, I configured the background.
# After that, I set the title of the root. 
# Last, I used .geometry to set the size of the window. 

my_font = Font(family="Arial", size = 22, weight = "bold")
my_frame = Frame(root)
my_frame.pack(pady = 10)
# Here, I am defining the font style of my To-Do list and the frame. 

my_list = Listbox(my_frame, font = my_font, width=40, height = 7, bg = "#cf9fff", 
bd=0, fg ="#5c4033", highlightthickness = 0, selectbackground = "#ff0000", activestyle = "none")
my_list.pack(side = LEFT, fill = BOTH) 
# Defining the listbox widget

my_scrollbar = Scrollbar(my_frame)
my_scrollbar.pack(side = RIGHT, fill = BOTH)
my_list.config(yscrollcommand = my_scrollbar.set)
my_scrollbar.config(command = my_list.yview)
# Defining the scrollbar for my app, it will be on the right.

my_entry = Entry(root, font=("Arial", 22), width = 26, bg = '#cf9fff')
my_entry.pack(pady=20)

button_frame = Frame(root, bg = '#a020f0')
button_frame.pack(pady=20)
# Defining the frame so I can organize my buttons. 

def deleteItem():
    my_list.delete(ANCHOR)
# This method will allow me to delete an item from my To-Do list
#  Used after I cross something off my list.

def addItem():
    my_list.insert(END, my_entry.get())
    my_entry.delete(0, END)
# This method will allow me to add items to my To-Do List.  
# For when I want to define what I need to do. 
#.insert will allow me to add elements from user input to my listbox. 
# .delete is to remove it from the entry widget so I can keep adding more elements. 

def cross_off_item():
    my_list.itemconfig(my_list.curselection(), fg = "dedede")
    my_list.selection_clear(0, END)
# This method will allow me to cross off items from my To-Do list. 
# .itemconfig is to change the font color so as I can differentiate it from 
# .selection_clear is so I can clear the selection I just made

def uncross_item():
    my_list.itemconfig(my_list.curselection(), fg = "5c4033")
    my_list.selection_clear(0, END)
#This method will allow me to uncross items from my list. 
# It does so by changing the color of the current selection to the original color. 
# I also have selection_clear for the same reason as well. 

def delete_crossed_item():
    count = 0
    
    while count < my_list.size():
        if my_list.itemcget(count, "fg") == "#dedede":
            my_list.delete(my_list.index(count))
        else:
            count += 1
# Here I am defining a method to delete crossed off items. 
# I use a counter to help me go through the list. 
# I will be using a while loop to help me go through the list. 
# To prevent the list from running an infinite amount of times, 
# if an item's text color does not match what I am looking for in 
# crossed off, I increment the counter by 1 and it evaluates the next iteration. 
# If it does match, it deletes the element from the listbox. 

delete_button = Button(button_frame, text = "Delete Item", command = deleteItem, bg = "#e7305b", font = ("arial", 12, "bold"))
add_button = Button(button_frame, text = "Add Item", command = addItem, bg = "#e7305b", font = ("arial", 12, "bold"))
cross_off_button = Button(button_frame, text = "Cross Off Item", command = cross_off_item, bg = "#e7305b", font = ("arial", 12, "bold"))
uncross_off_button = Button(button_frame, text = "Uncross Off Item", command = uncross_item, bg = "#e7305b", font = ("arial", 12, "bold"))
delete_crossed_off_button = Button(button_frame, text = "Delete Crossed Off Item", command = delete_crossed_item, bg = "#e7305b", font = ("arial", 12, "bold"))
# Here I created five button objects that the user can interact with so that the program can function properly. 
# Each button I defined the frame, the text for the buttons, what command to use when they are run, background color and font. 


delete_button.grid(row = 0, column = 0)
add_button.grid(row = 0, column = 1, padx = 20)
cross_off_button.grid(row = 0, column = 2)
uncross_off_button.grid(row = 0, column = 3, padx = 20)
delete_crossed_off_button.grid(row = 0, column = 4)
# Here I am organizing the buttons. I put all of them on row 0, but their columns are arranged vertically 
# I use padx to define the number of pixels to create padding between some of the buttons. 

root.mainloop()
# This tells Python to run the app and listen for events until I close the window. 


