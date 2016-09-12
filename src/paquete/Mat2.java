package paquete;

@SuppressWarnings("ConstantConditions")
public class Mat2 {

    public Integer[][] mat;
    private int elements = 0;           //para decir si esta llena o no
    private boolean hasCombined;        //para decir si se combinaron o no
    private boolean Win;
    private int score = 0;


    public Mat2(int n) {

        if (n > 10 || n < 3) {
            throw new IllegalArgumentException("Invalid Matrix Size");
        }

        Win = false;
        this.mat = new Integer[n][n];

    }

    /**
     * Constructor de 4x4
     */
    public Mat2() {

        this(4);

    }


    /**
     * asigna dos numeros 2 en la matriz en una posicion aleatoria
     */
    public void initialize() {
        addNewRandomCell();
        addNewRandomCell();
    }


    /**
     * esta funcion mueve de izq a derecha todos los elementos de la fila, despues de que estos fueron combinados
     *
     * @param dir      la direccion en la que se mueve la funcion
     * @param filaoCol es la fila o columna en la que se opera
     */
    public void Move(Direction dir, int filaoCol) {
        int from, to, indexDir, x, y, indexAux;

        boolean xaxis, yaxis;

        Object[] parameters = getDirectionParameters(dir, filaoCol);

        from = (int) parameters[0];
        to = (int) parameters[1];
        indexDir = (int) parameters[2];
        x = (int) parameters[3];
        y = (int) parameters[4];
        xaxis = (boolean) parameters[5];
        yaxis = (boolean) parameters[6];
        ////
        Integer[] aux = new Integer[mat.length];
        Integer Current;

        indexAux = from;


        while (from != to) {
            //////
            Current = mat[y][x]; //ELemeto Actual
            ///////

            if (Current != null) {

                aux[indexAux] = Current;
                indexAux += indexDir;
            }

            if (xaxis)
                x += indexDir;
            else if (yaxis)
                y += indexDir;

            from += indexDir;
        }
        if (xaxis) {
            mat[y] = aux;
        } else if (yaxis) {
            for (int ind = 0; ind < mat.length; ind++) {
                mat[ind][x] = aux[ind];
            }
        }
    }

    /**
     * Mueve la matrix y conbina todas  sus filas en la direccion indicada
     *
     * @param Direction la direccion en la que se mueve la funcion
     */
    public void Shift(Direction Direction) {
        Integer[][] copy = copiarMatriz();
        hasCombined = false;
        for (int x = 0; x < mat.length; x++) {
            combineCells(Direction, x);
            Move(Direction, x);
        }

        if (!this.esIgual(copy) && !hasCombined)    // con solo combinar ya se agrega una nueva celda dado que se mueve
            addNewRandomCell();
        else if (hasCombined)
            addNewRandomCell();
        gameOver();

    }

    /**
     * Devuelve los parametros para recorrer el arreglo en la direccion y orientacion correctas
     *
     * @param dir      la direccion en la que se mueve
     * @param filaoCol la fila o columna sobre la que se opera
     * @return Retorna un arreglo de objetos con los parametros, tanto int como boolean
     */
    private Object[] getDirectionParameters(Direction dir, int filaoCol) {
        int y, x, from, to, indexDir;
        boolean xaxis = false;
        boolean yaxis = false;

        if (dir.equals(Direction.LEFT)) {
            from = 0;
            to = mat.length;
            indexDir = 1;
            y = filaoCol;
            x = from;
            xaxis = true;
        } else if (dir.equals(Direction.RIGHT)) {
            from = mat.length - 1;
            to = -1;
            indexDir = -1;
            y = filaoCol;
            x = from;
            xaxis = true;
        } else if (dir.equals(Direction.UP)) {
            from = 0;
            to = mat.length;
            indexDir = 1;
            x = filaoCol;
            y = from;
            yaxis = true;
        } else {
            from = mat.length - 1;
            to = -1;
            indexDir = -1;
            x = filaoCol;
            y = from;
            yaxis = true;
        }

        return new Object[]{from, to, indexDir, x, y, xaxis, yaxis};
    }


    /**
     * combina las celdas que tengan mismo numero, revisa de izquierda a derecha, buscando un numero identico a la celda actual
     * una vez que los combina, pasa a buscar otra operacion
     *
     * @param dir      la direccion en la que se mueve
     * @param filaoCol la fila o columna sobre la que se opera
     * @return true si se realizo por lo menos una combinacion en alguna fila, o columna
     */
    public void combineCells(Direction dir, int filaoCol) {
        int from, to, indexDir, x, y;
        boolean xaxis, yaxis;

        Object[] parameters = getDirectionParameters(dir, filaoCol);


        from = (int) parameters[0];
        to = (int) parameters[1];
        indexDir = (int) parameters[2];
        x = (int) parameters[3];
        y = (int) parameters[4];
        xaxis = (boolean) parameters[5];
        yaxis = (boolean) parameters[6];

        boolean checkIfEquals;
        int bkp = -1;
        Integer Current;


        while (from != to) {

            checkIfEquals = true;
            ////
            Current = mat[y][x];
            /////

            if (Current != null) {

                if (bkp == -1) {
                    bkp = from;
                    checkIfEquals = false;
                }

                if (xaxis) {  //Si se mueve en el eje x

                    if (!Current.equals(mat[y][bkp])) {      //SI no es igual setea nuevo Bkp

                        bkp = x;
                        checkIfEquals = false;
                    } else if (checkIfEquals) {   //Si es igual

                        mat[y][x] = null;
                        mat[y][bkp] = mat[y][bkp] * 2;
                        this.score += mat[y][bkp];

                        if (mat[y][bkp] == 2048) {   //SI GANO
                            Win = true;
                        }
                        bkp = from;   // por si le sigue uno igual al resultado

                        this.elements--;       //disminuye en uno ya que al combinarse dos numeros se tiene un elemento menos
                        hasCombined = true;
                    }
                }

                if (yaxis) { //Si se mueve en el eje y

                    if (!Current.equals(mat[bkp][x])) {  //SI no es igual setea nuevo Bkp
                        bkp = y;
                    } else if (checkIfEquals) {   //Si es igual


                        mat[y][x] = null;
                        mat[bkp][x] = mat[bkp][x] * 2;
                        this.score += mat[bkp][x];

                        if (mat[bkp][x] == 2048) {//SI GANO
                            Win = true;
                        }

                        bkp = from;   //por si le sigue uno igual al resultado
                        this.elements--;       //disminuye en uno ya que al combinarse dos numeros se tiene un elemento menos
                        hasCombined = true;
                    }
                }

            }

            if (xaxis) {                    //Incremante el x

                x += indexDir;
            }                            // O

            if (yaxis) {
                y += indexDir;            //Incrementa el y
            }

            from += indexDir;
        }


    }


    private void addNewRandomCell(int cont, int x, int y) {
        Integer elem = mat[x][y];
        //si llego a su destino
        if (this.isFull())
            return;

        if (elem == null && cont == 0) {  //CASO BASE1
            this.mat[x][y] = aleatorio();
            this.elements++;
            return;

        }
        if (x == this.mat.length - 1 && y == this.mat.length - 1)  //CASO BASE2
            return;


        //esto es para que, como el valor de coordinate se obtiene a traves de random(),
        //se use para no buscar siempre la primer celda vacia, y busque varias de acuerdo al valor de coordinate
        int addOrNot = 0;
        if (elem == null) {
            addOrNot = 1;
        }

        if (y == this.mat.length - 1 && x < this.mat.length - 1)   //si llegue al final de una fila y no coloca
            addNewRandomCell(cont - addOrNot, x + 1, 0);     //pasa a la siguiente
        else
            addNewRandomCell(cont - addOrNot, x, y + 1);

        //lo mismo pasa si encuentra casillas llenas, solo que no disminuya coordinate
        //es mas para el caso en que queden pocas celdas vacias


    }


    /**
     * usada para buscar una posicion al azar de la matriz para agregar 2
     *
     * @return el numero entre 0 y la cantidad de celdas vacias que se usa para ubicar un 2 en alguna de ellas
     */
    private int randomPosition() {
        int totalElements = mat.length * mat.length;
        return (int) (Math.random() * (totalElements - this.elements));
    }


    /**
     * busca una posicion al azar y agrega un 2 en la matriz
     * de existir un numero en esa posicion busca otra
     */
    public void addNewRandomCell() {  // le cambie el nombre
        this.addNewRandomCell(randomPosition(), 0, 0);
    }


    public Integer[][] copiarMatriz() {
        Integer[][] copy = new Integer[mat.length][mat.length];
        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat.length; y++) {
                if (mat[x][y] != null)
                    copy[x][y] = mat[x][y];
            }
        }
        return copy;
    }


    @Override
    public boolean equals(Object another) {
        if (another == null)
            return false;

        if (another instanceof Mat2) {
            Mat2 matrix = (Mat2) another;

            if (matrix.mat.length != mat.length) {
                return false;
            }

            for (int x = 0; x < mat.length; x++) {
                for (int y = 0; y < mat.length; y++) {

                    Integer actualMat = mat[x][y];
                    Integer actualOther = matrix.mat[x][y];

                    if (actualMat != null && actualOther != null) {

                        if (!actualMat.equals(actualOther))
                            return false;
                    } else if (actualMat == null && actualOther != null)
                        return false;

                    else if (actualMat != null)
                        return false;
                }
            }
        } else {
            return false;
        }  //Si another no es un objeto de tipo Mat2

        return true;

    }


    /**
     * @return devuelve si esta llena la matriz
     */
    public boolean isFull() {
        return this.elements == mat.length * mat.length;
    }


    @Override
    public String toString() {
        String numeros = "";
        for (Integer[] aMat : this.mat) {
            for (int y = 0; y < this.mat.length; y++) {
                if (aMat[y] == null)
                    numeros += "- | ";
                else
                    numeros += aMat[y].toString() + " | ";
            }
            numeros += "\n";
        }

        return numeros;
    }


    /**
     * revisa si esta llena y si se combino alguna celda
     *
     * @return true si esta llena y no se combino, false si no esta llena o si se combino alguna
     */
    public boolean gameOver() {
        if (isFull() && !isCombinable())
            return true;
        else if (!isFull() || isCombinable())
            return false;
        return false;
    }


   public boolean isWin() {
        return Win;
    }

    /**
     * revisa si hay filas o columnas por combinar
     *
     * @return true si existe al menos una fila o columna para combinar
     */
    public boolean isCombinable() {
        boolean ret = false;
        for (int row = 0; row < mat.length; row++) {
            ret = ret || isCombinable(Direction.RIGHT, row) || isCombinable(Direction.DOWN, row);
        }
        return ret;
    }

    /**
     * revisa si hay filas o columnas por combinar en la direccion especificada
     *
     * @return true si existe al menos una fila o columna para combinar
     */
    private boolean isCombinable(Direction dir, int row_Column) {
        int from, to, indexDir, x, y;
        boolean xaxis, yaxis;
        Object[] parameters;

        if (dir.equals(Direction.LEFT) || dir.equals(Direction.RIGHT))
            parameters = getDirectionParameters(Direction.RIGHT, row_Column);
        else
            parameters = getDirectionParameters(Direction.DOWN, row_Column);

        from = (int) parameters[0];
        to = (int) parameters[1];
        indexDir = (int) parameters[2];
        x = (int) parameters[3];
        y = (int) parameters[4];
        xaxis = (boolean) parameters[5];
        yaxis = (boolean) parameters[6];

        boolean checkIfEquals;
        int bkp = -1;
        Integer Current;

        while (from != to) {
            checkIfEquals = true;
            ////
            Current = mat[y][x];
            /////
            if (Current != null) {

                if (bkp == -1) {
                    bkp = from;
                    checkIfEquals = false;
                }

                if (xaxis) {  //Si se mueve en el eje x

                    if (!Current.equals(mat[y][bkp]))      //SI no es igual setea nuevo Bkp
                        bkp = x;

                    else if (checkIfEquals)   //Si es igual
                        return true;

                }

                if (yaxis) { //Si se mueve en el eje y

                    if (!Current.equals(mat[bkp][x]))  //SI no es igual setea nuevo Bkp
                        bkp = y;

                    else if (checkIfEquals)   //Si es igual
                        return true;

                }

            }


            if (xaxis) {

                x += indexDir;
            }

            if (yaxis) {
                y += indexDir;
            }

            from += indexDir;
        }

        return false;
    }

    public boolean isRowOrColumnChanged(Direction dir, int row_Column) {
        Integer[][] copy = copiarMatriz();
        Mat2 m = new Mat2(copy.length);
        m.mat = copy;
        m.combineCells(dir, row_Column);
        m.Move(dir, row_Column);

        return !this.esIgual(copy);

    }


    private static int aleatorio() {
        //int option=(int)(Math.random()*10);

        return Math.random() < 0.9 ? 2 : 4;
        //return  num % 2 == 0 ? num : num+1;


    }

    public boolean esIgual(Integer[][] copy) {
        for (int x = 0; x < mat.length; x++) {
            for (int y = 0; y < mat.length; y++) {

                Integer actualMat = mat[x][y];
                Integer actualOther = copy[x][y];

                if (actualMat != null && actualOther != null) {

                    if (!actualMat.equals(actualOther))
                        return false;
                } else if (actualMat == null && actualOther != null)
                    return false;

                else if (actualMat != null)
                    return false;
            }
        }
        return true;
    }

    public int getScore() {
        return score;
    }

    public int getElements() {
        return elements;
    }

    public void setElements(int elements) {
        this.elements = elements;
    }

    public Integer[][] getMat() {
        return mat;
    }
}
