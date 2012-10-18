# Daifuku: A Model-Context-Interaction Example
This is a varient of Model-View-Presenter (MVP) that Martin Fowler has written
about.  Microsoft also use something similar (whose name escapes me) for web
apps.  I have used it in a few applications and found it to have considerable
advantages over Model-View-Controller (MVC).

## Model View Controller (MVC)
The most popular way of organizing UI code (other than just jamming it in
anywhere) is called Model View Controller (MVC). The idea is that you have Model
objects which hold the structure and logic of underlying problem domain
objects.  You have View objects which describe the way you represent those
objects to the user.  Finaly, you have Controller objects that describe the
way that the user interacts with the objects.  Though, it's not completely
correct, you can roughly view this as being problem domain processing objects,
output objects and input objects.

MVC has many advantages.  Abstracting the problem domain objects from
the way they are represented to the user is extremely useful.  However,
in practice it rarely works the way you would expect.  Usually, widget
libraries are implemented using MVC.  So, for instance, you might have
a text widget that contains a buffer for storing characters (a Model
object), a UI object for displaying the text widget to the user (a
View object) and if you are lucky a separate input object for gathering
and processing user input, coordinating putting characters into the
buffer and scheduling the View to be updated when the Model changes.
To be honest, virtually every GUI widget library cheats and puts the
Controller responsibilities into the View (but that's a story for another
day).

Anyway, when you have one of these widget libraries, you usually
subclass the GUI object(s) and put your business logic in the
subclass.  This sucks in any number of ways.  First, all your
business logic is wrapped up in the UI code, so you usually can't
do unit testing on it.  Second, you have a choice of using your own
model objects, or the model objects in the UI widgets.  If I have
a string that I want to edit, will I *actually* keep a string around
or will I just use the buffer in the TextEntry widget? If I do the
latter, I end up passing TextEntry widgets around my whole program.
If I do the former, I now have to make sure that the string updating
is synchronized with the TextEntry widget in the UI -- and I have
no reasonable place to put that code.  Finally, your program design
is now constrained by the inheretance hierarchy of the widget library,
which makes absolutely no sense what-so-ever.

## Using a "Context" Mediator
I have been experimenting with an alternative way of organizing
UI code.  It has a concept of a "Context".  A Context is an activity
that a user wants to do.  An example would be loading a file, or
entering data.  I would calls these FileLoadingContext and 
DataEnteringContext, respectively.  The Context is basically a
class that describes business logic.  It contains all the model
objects necessary to complete the task that it is doing.  It also
contains an Interaction.

The Interaction is simply a contract with the Context object.
It describes how information will be presented to the user and
provides a way for the user to interact with he Context.  The Interaction
has 3 different kinds of methods: Methods which update the Interaction
from the Model objects in the Context, methods which update
the Model objects in the Context from the Interaction, and methods which
request processing from the Context (useful when you have a computed
value in the view which doesn't exist directly in the model).  In
Java I have implemented the abstract Interaction as an inner interface
on the Context.  I have always had each Context object containing
exactly one abstract Interaction object, but there is no reason why
you can't have more.

The concrete Interaction objects are implementations of the
Interaction interfaces on the Contexts.  They contain objects from the
GUI widget library.  It is a good idea *not* to inherit from
the GUI widget library, since a FileLoadingInteraction should *use* a
FileDialog rather than being one.  What I often do is create
new Widget classes which are composite widgets from the library
that has a convenient interfact.  I then use those widgets in
my concrete Interaction classes.  This is useful if you ever decide to
go with a different UI widget library, for instance -- you just
have to reimplement those widgets.

Communication between the Interaction and the Context is explicit, and uses
the interface on the abstract view.  Methods that update model
objects, or request processing on the Context are usually exactly
one line in the Interaction: they call a method on the Context of the same
name.  Interactions are not allowed to update Model objects, they may only
inspect them.  They are also not allowed to call methods that
modify the objects.  All of that is done in the Context.
It's extra typing, but it solves huge numbers of problems since
all business logic exists in the Context.  In C++, I pass Model objects
as const references to the Interaction, which means that it can only
call const methods.  In Java, I can't remember, but I don't know
if you can do that...

Composition of UI objects is done in two ways.  First are the
Widget objects that the concrete Interaction uses.  You compose the
widget library objects any way you want in these.  You could
even use a GUI builder if you really wanted to (though, I hate
those). Every Interaction contains a top level widget that contains
all the other widgets it holds.  It also has methods called
add() and remove() that inserts (or removes) the top level widget
of a different Interaction. How it does that is dependent on the
Interaction (see below).

There is one special Context called the Application.  The Application
contains many of the other Contexts.  Each context has enter()
and exit() methods, the enter() method taking the parent Context
as a parameter (i.e., the Application will call aContext.enter(this)).
Usually I will implement the Interaction for the Application as a window
containing a vertically packed box.

When the Application is entered, it opens the Interaction (the window
containing the box).  The application will then enter the main
Contexts.  For example, there may be a CommandContext that
represents all the commands you can do in the application.  The
CommandContext Interaction will be a menu bar and a tool bar.  When
the CommandContext is entered, it creates its Interaction. It then
add()s that Interaction to it's parent Context's (the Application's)
Interaction.  So, in other words, it will insert a Box containing the menu
bar and tool bar into the Application Interaction's main widget (the
vertical box).  If a Context exits, it removes it's Interaction from
the parent context's Interaction.  If the Application exits... it
closes down ;-)



