from projetIA import noyau
from random import randrange

class IA(object):
    def __init__(self):
        self.arbre=[]
        self.game=noyau()
        
        
    def minMax_alphaBeta(self,board,prof,joeur):
        self.MIN=-1000
        self.MAX=1000
        if prof==0 or self.game.end==True:
            pass
