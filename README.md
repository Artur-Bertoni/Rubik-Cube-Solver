# Rubik Cube Resolver
Desenvolvido por [Artur Bertoni dos Santos](https://www.linkedin.com/in/artur-bertoni-dos-santos/) e [Maria Eduarda Fischer de Assumpção](https://www.linkedin.com/in/maria-eduarda-fischer-de-assumpção-3a22b9223/)

Programa desenvolvido a partir de um exercício proposto na faculdade com o objetivo de resolver um cubo mágico usando estratégias de busca em java. No momento, as informações do cubo são inseridas alterando o arquivo [cube.txt](https://github.com/Artur-Bertoni/Rubik-Cube-Solver/blob/main/cube/cube.txt). A solução apresentada é composta pelo conjunto de ações que devem ser feitas nas coordenadas do cubo.

Por Exemplo:

    F(2)tL -> C(0)tD -> C(0)tD -> R(2)tR -> C(2)tD -> F(2)tL

Para realizar essa operação, o cubo físico deve ser colocado com a face vermelha voltada para o usuário e a face branca à direita. (A cor de uma face é indicada pelo quadrado central da face)
#
A execução de 'R(n)tR' consiste em mover uma fileira (Row) da face (Face) frontal para a direita (Row n to Right). Em que 'n' representa qual fileira será movida, começando por 0 que indica a fileira mais acima e 2 a mais abaixo:
####
             Face Esquerda (LeftFace):                     Face Frontal (FrontFace):
             ------------------------------------          ------------------------------------
            || green     | red       | orange   ||        ||  white    | yellow   | yellow    ||
            || white     | yellow    | orange   ||        ||  white    | red      | red       ||
            || orange    | orange    | yellow   ||        ||  red      | red      | red       ||
             ------------------------------------          ------------------------------------

Movimento 'R(0)tR' é realizado, resultado:

             Face Frontal (FrontFace):                     Face Direita (RightFace):
             ------------------------------------          -----------------------------------
            || green     | red       | orange   ||        ||  white     | yellow   | yellow  ||
            || white     | red       | red      ||        ||  white     | white    | orange  ||
            || red       | red       | red      ||        ||  white     | white    | orange  ||
             ------------------------------------          -----------------------------------
####
Nota-se que o movimento realisado foi o de mover a primeira fileira (Row 0) da face frontal para a direita, resultando em uma situação onde a fileira 0 da face frontal se tornou a fileira 0 da face direita, e a fileira 0 da face esquerda se tornou a fileira 0 da face frontal.
#
O movimento 'C(n)tD' seria a movimentação de uma coluna 'n' da face frontal (FrontFace) para baixo (Column n to Down). 'n' segue a mesma lógica de movimentação citada ateriormente (0, 1, 2):
####
             Face Frontal (FrontFace):
             ------------------------------------
            || white     | yellow   | yellow    ||
            || white     | red      | red       ||
            || red       | red      | red       ||
             ------------------------------------
            
Executa-se o movimento 'C(2)tD':

             Face Inferior (DownFace):
             ------------------------------------
            || blue      | blue      | yellow   ||
            || yellow    | blue      | red      ||
            || white     | green     | red      ||
             ------------------------------------
####
Nota-se então que a coluna 2 (terceira coluna) da Face Frontal movimentou-se para baixo
#

Por fim, o movimento 'F(n)tR' retrata o movimento de uma face para a direita, ou seja, no sentido horário (Face n to Right). Neste movimento, 'n' recebe o número equivalente à face, onde 0 seria a face frontal, 1 a direita, 2 a trazeira, 3 a esquerda, 4 a superior e 5 a inferior.
####
             Face Trazeira (BackFace):
             ------------------------------------
            || white     | green     | green    ||
            || yellow    | orange    | orange   ||
            || green     | blue      | yellow   ||
             ------------------------------------

Movimento 'F(2)tR' é realizado:

            Face Trazeira (BackFace):
             ------------------------------------
            || green     | yellow    | white    ||
            || blue      | orange    | green    ||
            || yellow    | orange    | green    ||
             ------------------------------------
####
É possível notar que a face recebeu uma movimentação no sentido horário, onde a primeira coluna passou a ser a primeira linha, a última linha se tornou a primeira coluna e assim por diante, mantendo apenas o quadrado (Box) central inalterado.
#

O objetivo do programa é a partir de um status inicial do cubo, ele irá verificar se é uma solução de status...

    se for uma solução, ele retornará uma String com o passo a passo (Steps) para chegar na solução;

    Se não for, ele irá gerar os 9 movimentos possíveis e armazená-los em uma estrutura de dados (borda).

O programa repetirá os passos até que a solução seja encontrada ou o limite seja esvaziado.

### Instrução para o uso
Para testar a aplicação basta clocar o repositório na máquina local e executar o arquivo [Main](https://github.com/Artur-Bertoni/Rubik-Cube-Solver/blob/main/src/Main.java) dentro da IDE, utilizando a [JDK 17](https://www.techspot.com/downloads/7440-java-se-17.html) no projeto