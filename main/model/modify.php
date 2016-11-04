<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:48
 */




function modify($argModi, $argTarget, $argMenu){

    switch($argMenu){
        case 1:{
            if($argTarget) {
                $query = "update pstock set Pname = '" . $argModi['Pname'] . "', Pprice = '" . $argModi['Pprice'] . "', Pstock = '" . $argModi['Pstock'] . "'" . " where Pname = '" . $argTarget . "'";
                mysqli_query(DBconn(), $query);
            }
            else
            {
                for ($index_i = 0; $index_i < count($argModi['Pname']); $index_i++) {
                    $query = "update pstock set Pname = '" . $argModi['Pname'][$index_i] . "', Pprice = '" . $argModi['Pprice'][$index_i] . "', Pstock = '" . $argModi['Pstock'][$index_i] . "'" . " where Pname = '" . $argModi['Pname'][$index_i] . "'";
                    mysqli_query(DBconn(), $query);
                }

            }
            break;
        }

        case 2:{


            if($argTarget){
                $query = "update bstock set Bname = '" . $argModi['Bname'] . "', Bstock = '" . $argModi['Bstock'] ."'"." where Bname = '".$argTarget."'";
                mysqli_query(DBconn(), $query);
            }
            else{
                for($index_i = 0 ; $index_i < count($argModi['Bname']) ; $index_i ++){
                    $query = "update bstock set Bname = '" . $argModi['Bname'][$index_i] . "', Bstock = '" . $argModi['Bstock'][$index_i] ."'"." where Bname = '".$argModi['Bname'][$index_i]."'";
                    mysqli_query(DBconn(), $query);
                }
            }
            break;
        }
        default:{

        }
    }

}
