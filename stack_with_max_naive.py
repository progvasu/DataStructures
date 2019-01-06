#python3
import sys

class StackWithMax():
    def __init__(self):
        self.__stack = []

    def Push(self, a):
        self.__stack.append(a)

    def Pop(self):
        assert(len(self.__stack))
        self.__stack.pop()

    def Max(self):
        assert(len(self.__stack))
        return max(self.__stack)
    
    def IsEmpty(self):
        if (len(self.__stack) == 0):
            return True
        return False
        
    def Peek(self):
        if (len(self.__stack) != 0):
            return self.__stack[0]
        

if __name__ == '__main__':
    stack = StackWithMax()
    aux_stack = StackWithMax()

    num_queries = int(sys.stdin.readline())
    for _ in range(num_queries):
        query = sys.stdin.readline().split()

        if query[0] == "push":
            stack.Push(int(query[1]))
            if (aux_stack.IsEmpty):
                aux_stack.Push(int(query[1]))
            elif (int(query[1]) >= aux_stack.Peek()):
                aux_stack.Push(int(query[1]))
                
        elif query[0] == "pop":
            if (aux_stack.Peek() <= stack.Peek()):
                aux_stack.Pop()
            stack.Pop()
            
        elif query[0] == "max":
            if (aux_stack.IsEmpty()):
                pass
            else:
                print (aux_stack.Peek())
        else:
            assert(0)
