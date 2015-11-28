<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:48
 */

function modify($argModi, $argTarget, $argMenu){

    print_r($argModi);
    switch($argMenu){
        case 1:{
            for($index_i = 0 ; $index_i < count($argModi['Pname'][0]) ; $index_i ++){

                $query = "update pstock set Pname = '".$argModi['Pprice'][0][$index_i]."', Pprice = '".$argModi['Pprice'][0][$index_i]."', Pstock = '".$argModi['Pstock'][0][$index_i]."'";
                $query .= "' where Pname = '".$argTarget."'";
                mysqli_query(DBconn(), $query);
                echo $query;
            }

            break;
        }

        case 2:{

            for($index_i = 0 ; $index_i < count($argModi['Bname'][0]) ; $index_i ++) {
                $query = "update bstock set Bname = '" . $argModi['Bname'][0][$index_i] . "', Bstock = '" . $argModi['Bstock'][0][$index_i] ."'";
                $query .= "' where Bname = '".$argTarget."'";
                mysqli_query(DBconn(), $query);
                echo $query;
            }
            break;
        }
        default:{

        }
    }

}
