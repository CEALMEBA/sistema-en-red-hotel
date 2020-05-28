<?php


$host = "localhost";
$user = "id13826075_uv";
$password = "GQHtOIpQt*Ld6]>%";
$database = "id13826075_hotel";

$conn = mysqli_connect($host, $user, $password, $database);

if(isset($_GET['hab'])){
  $pet_activa = "SELECT luces,temperatura,llaves FROM Servicios
  WHERE habitacion = '$_GET[hab]'";
  $sql_statement = $conn->prepare($pet_activa);
  $sql_statement->execute();
  $result = $sql_statement->get_result();
}else{
  header('Location:index.php');
}

if ($result->num_rows > 0) {
  echo "<br />". "Servicos activos." . "<br />";
}else{
  echo "<br />". "Sin Servicos activas." . "<br />";
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
        <h1 class="icon-">Tablero </h1>
        <input type="checkbox" id="menu-bar">
        <label class="icon-menu" for="menu-bar"></label>
            </div>
</header>

        <main>
            <br>
              <section id="intro">
              <div class="contenedor">
                  <h2>- Servicios para la habitación <?php echo $_GET['hab']; ?>  -</h2>
                  <p></p>
                  </div>
              </section>

              <?php while ($row = $result->fetch_assoc()) { ?>

            <section id="blog">

                <div class="contenedor">


                  <article>
                      <div class="circular--portrait">
                          <img src="images\luces.jpg" >
                      </div>
                      <h4>Luces</h4>
                      <label class="switch">
                        <input id="luces" value='<?php echo $row['luces']; ?>' type="checkbox">
                        <span class="slider round"></span>
                      </label>
                  </article>

                  <article>
                      <div class="circular--portrait">
                          <img src="images\temperatura.jpg" >
                      </div>
                      <h4>Temperatura</h4>
                      <label class="switch">
                        <input id="temperatura" value='<?php echo $row['temperatura']; ?>' type="checkbox">
                        <span class="slider round"></span>
                      </label>
                      <br>
                      <br>
                      <div id="text">
                        <input id="grados" style="border-radius:20px; height:30px; text-align:center;" placeholder="Temperatura C°" value='<?php echo $row['temperatura']; ?>' type="text">
                      </div>

                  </article>

                  <article>
                      <div class="circular--portrait">
                          <img src="images\llave.jpg" >
                      </div>
                      <h4>Llaves</h4>
                      <label class="switch">
                        <input id="llaves" value='<?php echo $row['llaves']; ?>' type="checkbox">
                        <span class="slider round"></span>
                      </label>
                  </article>
                          <?php  } ?>

                </div>
            </section>

            <section id="intro">
            <div class="contenedor">
                <div class="botonperro">
                <button type="button" onclick="ajustar()">Ajustar habitación</button>
                </div>
                </div>
            </section>

            <script type="text/javascript" src="js/jquery.min.js"></script>
          	<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>

            <script>
            if($('#luces').val() == '1') {
              document.getElementById("luces").checked = true;
            }else{
              document.getElementById("luces").checked = false;
            }

            if($('#temperatura').val() > '0'){
              document.getElementById('grados').disabled = true;
              document.getElementById("temperatura").checked = true;
            }else{
              document.getElementById('grados').disabled = false;
              document.getElementById("temperatura").checked = false;
            }

            if($('#llaves').val() == '1'){
              document.getElementById("llaves").checked = true;
            }else{
              document.getElementById("llaves").checked = false;
            }

          </script>

          <script>

          function ajustar(){
            var ajuste = "<?php echo $_GET['hab']; ?>";
            $.get('servicios.php', { ajuste:ajuste},
                function(returnedData){
                  location.href = "http://hoteluv.000webhostapp.com/";

            });
          }

          </script>

          <script>
          $(document).ready(function() {

            var hab = "<?php echo $_GET['hab']; ?>";


              $('#luces').change(function() {
                  var luces;
                  if(this.checked) {
                    luces=1;
                  }else{
                    luces=0;
                  }
                  $.get('servicios.php', { hab:hab, luces:luces},
                      function(returnedData){
                           console.log(returnedData);
                  });
              });

              $('#temperatura').change(function() {
                  var temperatura;
                  if(this.checked) {
                    temperatura=document.getElementById('grados').value;
                    document.getElementById('grados').disabled = true;
                  }else{
                    temperatura=0;
                    document.getElementById('grados').disabled = false;
                  }
                  $.get('servicios.php', { hab:hab, temperatura:temperatura},
                      function(returnedData){
                           console.log(returnedData);
                  });
              });


              $('#llaves').change(function() {
                  var llaves;
                  if(this.checked) {
                    llaves=1;
                  }else{
                    llaves=0;
                  }
                  $.get('servicios.php', { hab:hab, llaves:llaves},
                      function(returnedData){
                           console.log(returnedData);
                  });
              });
          });
          </script>
