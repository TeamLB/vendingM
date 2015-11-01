<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-10-25
 * Time: 오후 9:53
 */

include '../model/db_func.php';
include '../model/login_func.php';
include '../model/product_func.php';
include '../model/stock_func.php';
include '../model/search_func.php';


$func = isset($_POST['func']) ? $_POST['func'] : null;
$mode = intval($func/100);




switch ($mode){


    /*
     * mode
     *      1: login
     *      2: stock
     *      3: product
     *      4: bill
     *      5: inquire
     */



    //login
    case 1:{

        //trying login
        if($func ==110){
            $id = $_POST['id'];
            $password = $_POST['password'];
            $status = login($id, $password);

            switch($status){

                //Login Successful
                case 0:{
                    $func = 200;
                    break;
                }

                //ID Error
                case -1:{
                    echo "<script>alert('Check ID Plz')</script>";
                    $func = 100;
                    break;
                }


                //Password Error
                case 1:{
                    echo "<script>alert('Check Password Plz')</script>";
                    $func = 100;
                    break;
                }
                default:

            }
        }
        break;
    }

    case 2:{

    }

    case 3:{

    }

    default:
}

//header("location : ../view/Main_view.php");

?>
<form action = '../view/Main_view.php' method='post'>
    <input type ='hidden' name ='func' value='$func'>
</form>

<!--<script>document.getElementsByTagName('form')[0].submit()</script>-->