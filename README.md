# IPaint450
#list of features
- Pick a shape(done)
- Pick a primary color(done)
- Pick a secondary color(done)
- Select shading type (done)
- Click and drag to draw a shape(done)
- Click and drag to select shapes(done)
- Click and drag to move selected shapes(done)
- Undo last action(done)
- Redo last action(done)
- Selected shapes have dashed outline(done)
- fixed bugs for cannot draw from other directions(done)
- strategy pattern: In ClickHandler ==> A DrawFatherCommand will mutate according to shape, and be injected into an RunCommand
- state pattern: In DrawOval,DrawRect,DrawTriangleCommands ==> A DrawHandler will mutate according to shading types

------------third checkin

- Copy selected shapes(done) (in CopyCommand: using a global copy-stack to point to the selected shapes )
- Paste copied shapes(done) (in PasteCommand: using deep-copy to add more instances to the mains stack)
- Delete selected shapes(done) (in DeleteCommand: using local stack to point to selected shapes, modify their visibility)

- singleton pattern: PaintCanvas Class encapsulate Canvas returning method and repaint method.
- singleton pattern: CommandHistory Class encapsulate "redraw-main-stack" function, reduce redundancy in other classes. 
- abstract class pattern: DrawFatherCommand Class is the abstract class to encapsulate shared fields and methods;

--------------fourth checkin

- Group selected shapes
- Ungroup selected shapes

#GitHubLink
https://github.com/lilRedBb/IPaint450.git

#DesignPatterns