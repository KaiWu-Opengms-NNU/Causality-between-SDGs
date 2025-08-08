import numpy as np
import statsmodels.tsa.stattools as ts
import sys

def ADF(x):
    result = ts.adfuller(x)
    return result


if __name__ == '__main__':
    a=[]
    for i in range(1,len(sys.argv)):
        a.append(sys.argv[i])
    b=np.float64(ADF(a)[0])
    c=np.float64(ADF(a)[1])
    print(str(b)+","+str(c))