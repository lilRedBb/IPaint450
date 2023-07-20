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


- Copy selected shapes
- Paste copied shapes
- Delete selected shapes
- Group selected shapes
- Ungroup selected shapes

#GitHubLink
https://github.com/lilRedBb/IPaint450.git

#DesignPatterns