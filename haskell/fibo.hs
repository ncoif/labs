import Criterion.Main

-- |recursive slow fibo
fiboSlow :: Int -> Int
fiboSlow 0 = 0
fiboSlow 1 = 1
fiboSlow n = fiboSlow (n-1) + fiboSlow (n-2)

-- |tail recursive fibo
fiboRec :: Int -> Int
fiboRec n = fiboRec_tmp 0 1 n
  where
    fiboRec_tmp _ x 1 = x
    fiboRec_tmp x y n = fiboRec_tmp y (x+y) (n-1)

-- |improved tailed recursion with "strictness"
fiboRec' :: Int -> Int
fiboRec' n = fiboRec_tmp n 0 1
  where
    fiboRec_tmp 1 _ x = x
    fiboRec_tmp n x y = fiboRec_tmp (n-1) y $! (x+y)

main = defaultMain [
  bgroup "10" [ bench "fiboSlow 10" $ whnf fiboSlow 10
              , bench "fiboRec 10" $ whnf fiboRec 10
              , bench "fiboRec' 10" $ whnf fiboRec' 10
              ]
  , bgroup "20" [ bench "fiboSlow 20" $ whnf fiboSlow 20
              , bench "fiboRec 20" $ whnf fiboRec 20
              , bench "fiboRec' 20" $ whnf fiboRec' 20
              ]
  , bgroup "35" [ bench "fiboSlow 35" $ whnf fiboSlow 35
              , bench "fiboRec 35" $ whnf fiboRec 35
              , bench "fiboRec' 35" $ whnf fiboRec' 35
              ]
       ]
