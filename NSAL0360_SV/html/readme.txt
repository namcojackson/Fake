"Generate JSP File"を実行後、下記の内容を手で書き換える必要があります。

Row 218～221  ('A')
Row 328～331  ('B')
Row 440～443  ('C')
Row 552～555  ('D')
Row 664～667  ('E')
Row 776～779  ('F')
Row 888～891  ('G')
Row 1000～1003  ('H')
Row 1112～1115  ('I')
Row 1224～1227  ('J')

	<td><ezf:inputButton name="SelectAll" value="Select All" htmlClass="pBtn6" /></td>
	<td><ezf:inputButton name="UnSelectAll" value="Un Select All" htmlClass="pBtn6" /></td>
	<td><ezf:inputButton name="InsertRow" value="Insert Row" htmlClass="pBtn6" /></td>
	<td><ezf:inputButton name="DeleteRow" value="Delete Row" htmlClass="pBtn6" /></td>
		↓
	<td><input type="button" class="pBtn6" value="Select All" name="SelectAll" onclick="execTableEvent('A', 'SelectAll');" <%= buttonDisable %>></td>
	<td><input type="button" class="pBtn6" value="Un Select All" name="UnSelectAll" onclick="execTableEvent('A', 'UnSelectAll');" <%= buttonDisable %>></td>
	<td><input type="button" class="pBtn6" value="Insert Row" name="InsertRow" onclick="execTableEvent('A', 'InsertRow');" <%= buttonDisable %>></td>
	<td><input type="button" class="pBtn6" value="Delete Row" name="DeleteRow" onclick="execTableEvent('A', 'DeleteRow');" <%= buttonDisable %>></td>
    ※execTableEventメソッドの第一引数には、表毎(A～J)に値を変更する必要があります。	