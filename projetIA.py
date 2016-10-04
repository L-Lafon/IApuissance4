from random import choice
  

class noyau(object):
    def __init__(self):
        self.reset()
        
    def reset(self):
        self.board=[[0 for i in range(7)]for j in range(6)]
        self.gg=False
        self.end=False
        
    def placer(self,col,joueur,board,show=None): #A REFAIRE
        """col>=0 and col>=6, joueur==1 or joueur==2, board is matrice(7x6)"""
        #tests parametres
        assert (col>=0 and col<=6), 'col>=0 and col>=6'
        assert (joueur==1 or joueur==2), 'joueur==1 or joueur==2'
        assert len(board[0])==7 and len(board)==6, 'board is matrice(7x6)'
        
        if board[0][col]!=0: #pas besoin de parcourir le tableau
            print("Colonne pleine !")
        else :
            for i in range(6):
                if i>0 and board[i-1][col]==0 and board[i][col]!=0 :
                    board[i-1][col] = joueur
                if board[i][col]==0 and i==5: board[i][col] = joueur
                
        if show!=None: #montrer le plateau de facon ethetique
            self.show(board)
        return board

    def isAlign(self):
        #parcours colonne
        for j in range(7):
            for i in range(3):
                if (self.board[i][j]!=0 and
                    self.board[i][j]==self.board[i+1][j] and
                    self.board[i+1][j]==self.board[i+2][j] and
                    self.board[i+2][j]==self.board[i+3][j]):
                    self.gg=True
                    print('GG colonne!',i,j)
                else: pass
        #parcours ligne
        for j in range(4):
            for i in range(6):
                if (self.board[i][j]!=0 and
                    self.board[i][j]==self.board[i][j+1] and
                    self.board[i][j+1]==self.board[i][j+2] and
                    self.board[i][j+2]==self.board[i][j+3]):
                    self.gg=True
                    print('GG ligne!',i,j)
                else: pass
        #parcours diag
        for j in range(4):
            for i in range(3):
                if (self.board[i][j]!=0 and
                    self.board[i][j]==self.board[i+1][j+1] and
                    self.board[i+1][j+1]==self.board[i+2][j+2] and
                    self.board[i+2][j+2]==self.board[i+3][j+3] ):
                    self.gg=True
                    print('GG diag \ !',i,j)
                if (self.board[i+3][j]!=0 and
                    self.board[i+3][j]==self.board[i+2][j+1] and
                    self.board[i+2][j+1]==self.board[i+1][j+2] and
                    self.board[i+1][j+2]==self.board[i][j+3] ):
                    self.gg=True
                    print('GG diag / !',i+3,j)

    def stopGame(self):
        _=0
        for i in range(7):
            if self.board[0][i]!=0: _+=1
        if _==7 or self.gg==True: #si toutes les colonnes sont empty ou si qqun a gagné
            self.end=True
            print('THE END')

    def play(self,joueur):
        assert (joueur==1 or joueur==2), 'joueur==1 or joueur==2'
        

    # ----------------------------------------------------------------------------
    def show(self,mat):
        """Affiche la matrice de facon esthétique (pour debbuggage)"""
        col_sep, row_sep = '|', '\n%s+\n'%('+-----'*len(mat[0]))
        s=row_sep
        for i in range(len(mat)):
            s+=col_sep
            for j in range(len(mat[0])):
                s+='%5s%s'%(mat[i][j],col_sep)
            s+=row_sep
        return print(s)

    

if __name__=='__main__':
    x=noyau()
##x.placer(0,1,False)
##x.placer(1,1,False)
##x.placer(2,1,False)
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(1,1,False)
##x.placer(1,1,False)
##x.placer(2,1,False)
##x.placer(2,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(3,1,True)

# Debug the end
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(0,1,False)
##x.placer(1,1,False)
##x.placer(1,1,False)
##x.placer(1,1,False)
##x.placer(1,1,False)
##x.placer(1,1,False)
##x.placer(1,1,False)
##x.placer(2,1,False)
##x.placer(2,1,False)
##x.placer(2,1,False)
##x.placer(2,1,False)
##x.placer(2,1,False)
##x.placer(2,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(3,1,False)
##x.placer(4,1,False)
##x.placer(4,1,False)
##x.placer(4,1,False)
##x.placer(4,1,False)
##x.placer(4,1,False)
##x.placer(4,1,False)
##x.placer(5,1,False)
##x.placer(5,1,False)
##x.placer(5,1,False)
##x.placer(5,1,False)
##x.placer(5,1,False)
##x.placer(5,1,False)
##x.placer(6,1,False)
##x.placer(6,1,False)
##x.placer(6,1,False)
##x.placer(6,1,False)
##x.placer(6,1,False)
##x.placer(6,1,True)


#    x.isAlign()
#    x.stopGame()
