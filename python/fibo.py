def fibo_recu(n):
  """
  Compute the nth element of Fibonacci in a recursive (slow) way
  :param n
  :raise ValueError: if n negative
  """

  if n <= 0:
    raise ValueError("value must be positive Interger")

  if n == 1:
    return 1
  elif n == 2:
    return 1
  else:
    return fibo_recu(n-1) + fibo_recu(n-2)

def fibo_loop(n):
  """
  Compute the nth element of Fibonacci in a non-recursive (faster) way
  :param n
  """

  n1 = 1
  n2 = 1
  result = 1

  for x in range(2,n):
    result = n1 + n2
    n2 = n1
    n1 = result

  return result

