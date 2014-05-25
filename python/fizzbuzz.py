def fizzbuzz(n):
  """
  The classic fizzbuzz program
  from 1 to n
  """

  for x in range(1,n):
    if (x % 3 == 0) & (x % 5 == 0):
      print("fizzbuzz")
    elif x % 3 == 0:
      print("fizz")
    elif x % 5 == 0:
      print("buzz")
    else:
      print(x)

