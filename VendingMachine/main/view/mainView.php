<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:02
 */


session_start();
?>




<html>

    <body>
        <?php
            $func = isset($_REQUEST['func']) ? $_REQUEST['func'] : null;
            $menu = intval($func/100);
            switch($menu){
                case 1:{
                    if($func == 100)
                    {
                        $page = "Productmodi.php";
                    }
                    elseif($func = 120){
                        $page = "Productstock.php";
                    }

                    break;
                }

                case 2:{
                    if($func == 200)
                    {
                        $page = "Billmodi.php";
                    }
                    elseif($func = 220){
                        $page = "Billstock.php";
                    }
                    break;
                }

                case 3:{
                    $page = "salesList.php";
                    break;
                }
                default:{
                    $page = "main.php";
                }
            }

            include (dirname(__FILE__)."/body/$page");
        ?>
    </body>
</html>
