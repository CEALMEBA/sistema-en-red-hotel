<?php


$host = "localhost";
$user = "id13826075_uv";
$password = "GQHtOIpQt*Ld6]>%";
$database = "id13826075_hotel";

$conn = mysqli_connect($host, $user, $password, $database);
$pet_activa = "SELECT DISTINCT * FROM Peticiones
WHERE estado = '1'";
$sql_statement = $conn->prepare($pet_activa);
$npeticion = 0;
$sql_statement->execute();
$result = $sql_statement->get_result();

if ($result->num_rows > 0) {
  $npeticion = $result->num_rows;
  echo "<br />". "Peticiones activas." . "<br />";
}else{
  $npeticion = 0;
  echo "<br />". "Sin Peticiones activas." . "<br />";
}

?>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title></title>
            <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1, maximum-scale=1, minium-scale=1">
            <link rel="stylesheet" href="css/estilos.css">
            <link rel="stylesheet" href="css/fontello.css">
    </head>

    <body>
<header>
    <div class="contenedor">
        <h1 class="icon-">Hotel</h1>
        <input type="checkbox" id="menu-bar">
        <label class="icon-menu" for="menu-bar"></label>
            </div>
</header>

        <main>
            <br>
            <?php
            if($npeticion!=0){ ?>

              <section id="intro">
              <div class="contenedor">
                  <h2>- Habitaciones con petición -</h2>
                  <p></p>
                  </div>
              </section>

            <?php }else{ ?>

              <section id="intro">
              <div class="contenedor">
                  <h2>- No hay habitación con petición -</h2>
                  <p></p>
                  </div>
              </section>

            <?php } ?>


            <section id="blog">
                <div class="contenedor">
                  <?php while ($row = $result->fetch_assoc()) { ?>

                    <article>
                        <div class="circular--portrait">
                            <img src="images\hab<?php echo $row['habitacion']?>.jpg" onclick="location.href='http://hoteluv.000webhostapp.com/tablero.php?hab=<?php echo $row['habitacion']?>';" >
                        </div>
                        <h4>Habitación <?php echo $row['habitacion']?></h4>
                        <h4>Requiere: </h4>
                        <h4><?php

                        if(is_null($row['pluces'])){

                        }else if($row['pluces']==0){
                          echo "♠ Apagar Luces ";
                        }else if($row['pluces']==1){
                          echo "♠ Encender Luces ";
                        }

                        ?>
                        </h4>
                        <h4><?php
                        if(is_null($row['ptemperatura'])){

                        }else if($row['ptemperatura'] == 0){
                          echo "♠ Apagar Temperatura ";
                        }else if($row['ptemperatura'] > 0){
                          echo "♠ Temperatura a: ".$row['ptemperatura']." ";
                        }

                        ?>
                      </h4>
                      <h4><?php
                      if(is_null($row['pllaves'])){

                      }else if($row['pllaves'] ==0){
                        echo "♠ Cerrar puerta";
                      }else if($row['pllaves'] ==1){
                        echo "♠ Abrir puerta";
                      }
                      ?>
                    </h4>

                    </article>


                      <?php  } ?>
                </div>
            </section>







<!-- Primer pull request de Yiz gay-->
<!-- Primer pull request de Mota -->
