<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html">
<html>
<head>
<meta charset="UTF-8">
</head>
<body>

	<section class="ftco-section ftco-degree-bg">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 ftco-animate">
					<table class="table">
					<h2 class="mb-3">${vo.summary }</h2>
					<p>${vo.summary_in }</p>
					<p>
						<img src="${vo.poster }" alt="" class="img-fluid">
					</p>
					<p>Molestiae cupiditate inventore animi, maxime sapiente optio,
						illo est nemo veritatis repellat sunt doloribus nesciunt! Minima
						laborum magni reiciendis qui voluptate quisquam voluptatem soluta
						illo eum ullam incidunt rem assumenda eveniet eaque sequi deleniti
						tenetur dolore amet fugit perspiciatis ipsa, odit. Nesciunt dolor
						minima esse vero ut ea, repudiandae suscipit!</p>
						</table>
					<h2 class="mb-3 mt-5">#2. Creative WordPress Themes</h2>
					<p>Temporibus ad error suscipit exercitationem hic molestiae
						totam obcaecati rerum, eius aut, in. Exercitationem atque quidem
						tempora maiores ex architecto voluptatum aut officia doloremque.
						Error dolore voluptas, omnis molestias odio dignissimos culpa ex
						earum nisi consequatur quos odit quasi repellat qui officiis
						reiciendis incidunt hic non? Debitis commodi aut, adipisci.</p>
					<p>
						<img src="images/image_2.jpg" alt="" class="img-fluid">
					</p>
					<p>Quisquam esse aliquam fuga distinctio, quidem delectus
						veritatis reiciendis. Nihil explicabo quod, est eos ipsum. Unde
						aut non tenetur tempore, nisi culpa voluptate maiores officiis
						quis vel ab consectetur suscipit veritatis nulla quos quia
						aspernatur perferendis, libero sint. Error, velit, porro. Deserunt
						minus, quibusdam iste enim veniam, modi rem maiores.</p>
					<p>Odit voluptatibus, eveniet vel nihil cum ullam dolores
						laborum, quo velit commodi rerum eum quidem pariatur! Quia fuga
						iste tenetur, ipsa vel nisi in dolorum consequatur, veritatis
						porro explicabo soluta commodi libero voluptatem similique id
						quidem? Blanditiis voluptates aperiam non magni. Reprehenderit
						nobis odit inventore, quia laboriosam harum excepturi ea.</p>
					<p>Adipisci vero culpa, eius nobis soluta. Dolore, maxime ullam
						ipsam quidem, dolor distinctio similique asperiores voluptas enim,
						exercitationem ratione aut adipisci modi quod quibusdam iusto,
						voluptates beatae iure nemo itaque laborum. Consequuntur et
						pariatur totam fuga eligendi vero dolorum provident. Voluptatibus,
						veritatis. Beatae numquam nam ab voluptatibus culpa, tenetur
						recusandae!</p>
					<p>Voluptas dolores dignissimos dolorum temporibus, autem
						aliquam ducimus at officia adipisci quasi nemo a perspiciatis
						provident magni laboriosam repudiandae iure iusto commodi debitis
						est blanditiis alias laborum sint dolore. Dolores, iure,
						reprehenderit. Error provident, pariatur cupiditate soluta
						doloremque aut ratione. Harum voluptates mollitia illo minus
						praesentium, rerum ipsa debitis, inventore?</p>
					<div class="tag-widget post-tag-container mb-5 mt-5">
						<div class="tagcloud">
							<a href="#" class="tag-cloud-link">Life</a> <a href="#"
								class="tag-cloud-link">Sport</a> <a href="#"
								class="tag-cloud-link">Tech</a> <a href="#"
								class="tag-cloud-link">Travel</a>
						</div>
					</div>

					<div class="about-author d-flex p-4 bg-light">
						<div class="bio align-self-md-center mr-4">
							<img src="${vo.image }" style="height:50px;width:50px;" alt="Image placeholder"
								class="img-fluid mb-4">
						</div>
						<div class="desc align-self-md-center">
							<h3>${vo.made }</h3>
							<p>${vo.tip }</p>
						</div>
						
					</div>
					
					
				</div>
				    <div class="col-lg-4 sidebar ftco-animate">
            <div class="sidebar-box">
              <form action="#" class="search-form">
                <div class="form-group">
                  <span class="icon ion-ios-search"></span>
                  <input type="text" class="form-control" placeholder="Search...">
                </div>
              </form>
            </div>
            <div class="sidebar-box ftco-animate">
            	<h3 class="heading">Categories</h3>
              <ul class="categories">
                <li><a href="#">Vegetables <span>(12)</span></a></li>
                <li><a href="#">Fruits <span>(22)</span></a></li>
                <li><a href="#">Juice <span>(37)</span></a></li>
                <li><a href="#">Dries <span>(42)</span></a></li>
              </ul>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Hot Recipe</h3>
              <c:forEach var="hvo" items="${list }">
              <div class="block-21 mb-4 d-flex">
                <a class="blog-img mr-4" style="background-image: url(${hvo.poster});"></a>
                <div class="text">
                  <h3 class="heading-1"><a href="../recipe/recipe_detail.do?no=${hvo.no }">${hvo.summary }</a></h3>
                  <div class="meta">
                    <div><a href="#"><span class="icon-calendar"></span> ${hvo.regdate }</a></div>
                    <div><a href="#"><span class="icon-person"></span> ${hvo.made }</a></div>
                    <div><a href="#"><span class="icon-chat"></span> ${hvo.hit }</a></div>
                  </div>
                </div>
              </div>
              </c:forEach>
              </div>
            

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Tag Cloud</h3>
              <div class="tagcloud">
                <a href="#" class="tag-cloud-link">고기</a>
                <a href="#" class="tag-cloud-link">곡류</a>
                <a href="#" class="tag-cloud-link">과일</a>
                <a href="#" class="tag-cloud-link">가공식품</a>
                <a href="#" class="tag-cloud-link">채소</a>
                <a href="#" class="tag-cloud-link">해산물</a>
                <a href="#" class="tag-cloud-link">1인분</a>
              </div>
            </div>

            <div class="sidebar-box ftco-animate">
              <h3 class="heading">Paragraph</h3>
              <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ducimus itaque, autem necessitatibus voluptate quod mollitia delectus aut, sunt placeat nam vero culpa sapiente consectetur similique, inventore eos fugit cupiditate numquam!</p>
            </div>
			</div>
			
		</div>
	</section>
</body>
</html>