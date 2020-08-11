给定一个二维的矩阵，包含 'X' 和 'O'（字母 O）。

找到所有被 'X' 围绕的区域，并将这些区域里所有的 'O' 用 'X' 填充。

示例:

X X X X
X O O X
X X O X
X O X X
运行你的函数后，矩阵变为：

X X X X
X X X X
X X X X
X O X X

解释:

被围绕的区间不会存在于边界上，换句话说，任何边界上的 'O' 都不会被填充为 'X'。 任何不在边界上，或不与边界上的 'O' 相连的 'O' 最终都会被填充为 'X'。
如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/surrounded-regions
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。

class Solution {
    public void solve(char[][] board) {
        int row=board.length;
        if(row==0) return;
        int col=board[0].length;
        boolean[][] v=new boolean[row][col];
        for(int i=0;i<col;i++){
            if(board[0][i]=='O')
               dfs(board,row,col,0,i,v);
            if(board[row-1][i]=='O')
               dfs(board,row,col,row-1,i,v);
        }

        for(int i=0;i<row;i++){
            if(board[i][0]=='O')
               dfs(board,row,col,i,0,v);
            if(board[i][col-1]=='O')
               dfs(board,row,col,i,col-1,v);
        }

        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(board[i][j]=='*'){
                    board[i][j]='O';
                }else if(board[i][j]=='O'){
                    board[i][j]='X';
                }
            }
        }
    }
    private void dfs(char[][] board,int row,int col,int i,int j,boolean[][] v){
        if(i<0||i>=row||j<0||j>=col||board[i][j]=='X'||v[i][j]){
            return;
        }
        board[i][j]='*';
        v[i][j]=true;
        dfs(board,row,col,i+1,j,v);
        dfs(board,row,col,i-1,j,v);
        dfs(board,row,col,i,j+1,v);
        dfs(board,row,col,i,j-1,v);
    }
}