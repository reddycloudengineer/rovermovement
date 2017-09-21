class Solution(object):
    def leftMovement(self,position):
        lefDict={'N':'W','S':'E','E':'N','W':'S'}
        position[2]=lefDict[position[2]]
        return position
    def rightMovement(self,position):
        rightDict={'W':'N','E':'S','N':'E','S':'W'}
        position[2]=rightDict[position[2]]
        return position
    def move(self,position,maxPosition):

        dictMovement={'N':[0,1],'S':[0,-1],'E':[1,0],'W':[-1,0]}
        moveList=dictMovement[position[2]]
        for i in range(0,2):
            position[i]=int(position[i])+moveList[i]
            position[i]= position[i] if position[i]>=maxPosition[i] else position[i]
            position[i]=str(position[i])
        return position
    def roverMovement(self, position,movementTracker,maxPosition):
        movementTracker=list(movementTracker)

        for each in movementTracker:
            if each == 'L':
                position=self.leftMovement(position)
            elif each == 'R':
                position=self.rightMovement(position)
            else:
                position=self.move(position,maxPosition)
        print " ".join(position)
        return position

if __name__ == "__main__":

    s = \
        """5 5
    1 2 N
    LMLMLMLMM
    3 3 E
    MMRMMRMRRM"""

    lines = s.split('\n')
    # print(lines)

    listLen=len(lines)
    sol=Solution()
    for i in xrange(1,listLen,2):
        iniPosition=lines[i]
        instSeries=lines[i+1]
        sol.roverMovement(list(iniPosition.strip().split()),instSeries.strip(),lines[0].strip().split())


