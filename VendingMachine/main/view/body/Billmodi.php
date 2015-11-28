<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:21
 */

$list = isset($_SESSION['info_list']) ? $_SESSION['info_list'] : null;

?>


<body>
    <form action="../controller/mainCtrl.php?func=210" method="post">
        <?php
            for($index_i = 0 ; $index_i < count($list) ; $index_i++){
                $index_img = $index_i+1;
                echo "<img src='../img/b$index_img.jpg'>";
                echo "권액명&nbsp:&nbsp<input type='text' name = 'Bname[]' value='".$list[$index_i]['Bname']."'>";
                echo "수&nbsp량&nbsp:&nbsp<input type='text' name = 'Bstock[]' value='".$list[$index_i]['Bstock']."'>";
                echo "<a href='../controller/mainCtrl.php?func=210&target=".$list[$index_i]['Bname']."'".">수정</a>";
                echo "<Br>";
            }
        ?>

        <input type="submit" value="일괄 수정">
    </form>
</body>
