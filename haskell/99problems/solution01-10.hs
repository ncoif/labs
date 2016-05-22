-- |1 last element of a list
myLast :: [x] -> x
myLast (x:[]) = x
myLast (_:xs) = myLast xs

-- |myLast improve for empty lists
myLast' :: [x] -> Maybe x
myLast' [] = Nothing
myLast' x = Just (myLast x)

-- |2 Find the last but one element of a list
myButLast :: [x] -> x
myButLast [] = error "No end for empty lists!"
myButLast [_] = error "Too few elements"
myButLast (x:_:[]) = x
myButLast (_:xs) = myButLast xs

-- |3 Find the K'th element of a list, first element of the list is number 1
elementAt :: [x] -> Int -> x
elementAt (_) 0 = error "The list start at 1 (for the fun)"
elementAt [] _ = error "Cant work with empty list"
elementAt (x:_) 1 = x
elementAt (_:xs) n = elementAt xs (n-1)

-- |4 Find length of the list
myLength :: [x] -> Int
myLength [] = 0
myLength (_:x) = (myLength x) + 1

-- myLength [1..100000000]
-- Exception: stack overflow
myLength' :: [x] -> Int
--myLength' = sum . map ( \_ -> 1 )
--myLength' x = (sum . map ( \_ -> 1 )) x
myLength' x = sum ( map ( \_ -> 1) x )

--todo: investigate tail-recursion
myLength'' :: [x] -> Int
myLength'' list = myLength_acc list 0
  where
    myLength_acc [] n = n
    myLength_acc (_:xs) n = myLength_acc xs (n+1)

--todo: investigate tail-recursion
myLength''' :: [x] -> Int
myLength''' list = myLength_acc list 0
  where
    myLength_acc [] n = n
    myLength_acc (_:xs) n = myLength_acc xs $! (n+1)
     -- the trick here, $! to force the scrict evaluation (by opposition to lazy)

-- some interresting links
-- http://stackoverflow.com/questions/993112/what-does-the-exclamation-mark-mean-in-a-haskell-declaration#993326
-- https://www.fpcomplete.com/blog/2012/09/ten-things-you-should-know-about-haskell-syntax
-- http://stackoverflow.com/questions/13042353/does-haskell-have-tail-recursive-optimization?rq=1
-- http://stackoverflow.com/questions/33923/what-is-tail-recursion

-- Nothing better than the source code, every time
-- http://hackage.haskell.org/package/base-4.9.0.0/docs/src/GHC.Base.html

-- |5 Reverse a list
myReverse :: [x] -> [x]
myReverse [] = []
myReverse (x:xs) = myReverse xs ++ [x]

-- |6 find out if the list is a palindrome
isPalindrome :: (Eq x) => [x] -> Bool
isPalindrome [] = True
isPalindrome xs = xs == myReverse xs

isPalindrome' :: (Eq x) => [x] -> Bool
isPalindrome' [] = True
isPalindrome' xs = (head xs == last xs) && (isPalindrome $ tail $ init xs)

-- |7 flaten a nested structure

-- first define a new data type
data NestedList a = Elem a | List [NestedList a ]

myFlatten :: NestedList x -> [x]
myFlatten (Elem x) = [x]
myFlatten (List (x:xs)) = myFlatten x ++ myFlatten (List xs)
myFlatten (List []) = []
--myFlatten List (x:xs) = myFlatten x ++ myFlatten (List xs) --syntax error

-- |8 Eliminate consedutive duplicate of a list
myCompress :: (Eq a) => [a] -> [a]
myCompress [] = []
myCompress [x] = [x]
myCompress (a:as)
  | a == head as   = myCompress as
  | otherwise      = a :  myCompress as

-- |9 pack consecutive duplicate elements
myPack :: (Eq a) => [a] -> [[a]]
myPack [] = []
-- the trick i to use "span" (cheat maybe??)
myPack (x:xs) = let (first, rest) = span (==x) xs in (x:first) : myPack rest 

-- |10 "encode" the list (result of problem 9 but with character count)
myEncode :: (Eq a) => [a] -> [(Int, a)]
myEncode [] = []
myEncode a = map (\x -> (length x, head x)) (myPack a)

myEncode' :: (Eq a) => [a] -> [(Int, a)]
myEncode' a = map lambda (myPack a)
  where lambda x = (length x, head x)
