# 画像処理工学

[濃度反転](p1_Inverse_Mono_Click.scala)

[諧調度変換](p1_Level_Click.scala)

## 線形補間法

エッジ部分のギザギザを軽減するために用いられる

[線形補間法](p1_Size_Click.scala)
![img.png](../../../../docs/png/img.png)

数式

$$` z' = (1-q){(1-q)*z[i,j] + p*z[i+1,j]} +q{[1-p]*z[i,j+1] + p*z[i+1,j+1]} `$$

## 2.濃度変換

画像の明るい部分と暗い部分の明るさの比をコントラスト**contrast**という

線形な濃度変換

$`z' = \left\{
\begin{array}{ll}
0 & (0 \leq a) \\
\frac{z-a}{b-a} Z_m & (a \leq z \leq b)  \\
Z_m & (b < z \leq Z_m)
\end{array}
\right.
`$

原画像の画素数が`[0.a]`および`[b,Zm]`の範囲で完全に0でなければ画像情報の一部が失われる


非線形な濃度変換

$` z' = z_m (\frac{z}{z_m})^\gamma `$