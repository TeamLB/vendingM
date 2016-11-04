<?php
/**
 * Created by PhpStorm.
 * User: JongYoon
 * Date: 2015-11-28
 * Time: 오후 2:21
 */
header("Content-Type: text/html; charset=UTF-8");
$list = isset($_SESSION['info_list']) ? $_SESSION['info_list'] : null;
//var_dump($list);

?>


<body>
<form action="../controller/mainCtrl.php?func=110" method="post">
    <?php
    for($index_i = 0 ; $index_i < count($list) ; $index_i++){
        $index_img = $index_i+1;
        echo "<a href = '../controller/mainCtrl.php?func=120&target=".$list[$index_i]['Pname']."'>";
        echo "<img src='../img/p$index_img.jpg'>";
        echo "</a>";
        echo "제품명&nbsp:&nbsp<input type='text' name = 'Pname[]' value='".$list[$index_i]['Pname']."'>";
        echo "제품가&nbsp:&nbsp<input type='text' name = 'Pprice[]' value='".$list[$index_i]['Pprice']."'>";
        echo "재고량&nbsp:&nbsp<input type='text' name = 'Pstock[]' value='".$list[$index_i]['Pstock']."'>";
        echo "<Br>";
    }
    ?>
    <br>
    <a href="../controller/mainCtrl.php?func">메인 화면</a><br>
    <input type="submit" value="일괄 수정">
</form>
</body>
