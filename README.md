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

- Group selected shapes(done)
- Ungroup selected shapes(done)
- pattern: decorative pattern used in SelectGroupCommand & RefreshCanvas(GUI package)
- changes: 
- in Commands package
- DrawFatherCommand added 2 fields, "belongGroups"&"historyGroups", for shape to journal its group.
- DrawFatherCommand added 1 fields, "showAsSelected"; when group is selected, all members are also selected, but only group showAsSelected.
- DrawFatherCommand added 1 public function "addOrPopMyGroup", for shapes to comply with group/ungroup action. 
- /
- GroupCommand added 2 fields, "myMembers"&"historyMembers", for group to journal its members.
- GroupCommand has two constructors: 1 for new group created, 1 for old group being pasted.
- GroupCommand public function "addOrPopMyMembers", for Groups to comply with group/ungroup action.
- GroupCommand public function "membersClone", comply with pasteCommand to paste a group.
- /
- UnGroupCommand: by manipulating the journal List in both shape and group, achieve ungroup action.
- /
- SelectGroupCommand: as decorator for SelectCommand, when shapes and their groups are selected at the same time, un-select the groups. groups remain selected will select their member-shape, but, member shapes not showAsSelected.
- /
- PasteCommand: if were to copy a group, first create new groupCommand, then pass in the cloned-members.
- in Persistence package
- MakeGroupFrame: calculates group's members' coordinates and return in PairPoint(startPoint,endPoint).
- in GUI package
- RefreshCanvas: encapsulate the repaint action for all commands.



#GitHubLink
https://github.com/lilRedBb/IPaint450.git

#DesignPatterns