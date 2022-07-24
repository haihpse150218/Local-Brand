<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp"%>
<body>
	<!-- Page Header Start -->
	<div class="container-fluid bg-secondary mb-5">
		<div
			class="d-flex flex-column align-items-center justify-content-center"
			style="min-height: 300px">
			<h1 class="font-weight-semi-bold text-uppercase mb-3">Checkout</h1>
		</div>
	</div>
	<!-- Page Header End -->


	<!-- Checkout Start -->
	<form action="/localbrand/web/cart/checkout.do">
		<div class="container-fluid pt-5">
			<div class="row px-xl-5">
				<div class="col-lg-8">
					<div class="mb-4">
						<h4 class="font-weight-semi-bold mb-4">Receive Address</h4>
						<div class="row">
							<div class="col-md-6 form-group">
								<label>Name</label> <input class="form-control" type="text"
									name="name" value="${user.name}" placeholder="Johny Depp">
							</div>
							<div class="col-md-6 form-group">
								<label>Email</label> <input class="form-control" type="text"
									name="email" value="${user.email}">
							</div>
							<div class="col-md-6 form-group">
								<label>Mobile No</label> <input class="form-control" type="text"
									name="phone" value="${user.phone}">
							</div>
							<div class="col-md-6 form-group">
								<label>Address</label> <input class="form-control"
									name="address" value="${user.address}">
							</div>
							<div class="col-md-6 form-group">
								<label>City</label> <select class="custom-select"
									onchange="print_distric(this.selectedIndex);" id="state" name="city">
								</select>
							</div>
							<div class="col-md-6 form-group">
								<label>District</label> <select class="custom-select"
									id="distric" name="district">
								</select>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-4">
					<div class="card border-secondary mb-5">
						<div class="card-header bg-secondary border-0">
							<h4 class="font-weight-semi-bold m-0">Order Total</h4>
						</div>
						<div class="card-body">
							<h5 class="font-weight-medium mb-3">Products</h5>
							<c:forEach var="key"
								items="${sessionScope.cart.getMap().keySet()}">
								<c:if test="${cart.getMap().get(key).getQuantity()>0}">
									<div class="d-flex justify-content-between">
										<p>${cart.getMap().get(key).getProduct().getName()}_${cart.getMap().get(key).getQuantity()}</p>
										<p>
											<fmt:formatNumber
												value="${cart.getMap().get(key).getProduct().getPrice()*(1-cart.getMap().get(key).getProduct().getDiscount())
														*cart.getMap().get(key).getQuantity()}"
												type="currency" />
										</p>
									</div>
								</c:if>
							</c:forEach>
							<hr class="mt-0">
							<div class="d-flex justify-content-between mb-3 pt-1">
								<h6 class="font-weight-medium">Total Cost</h6>
								<h6 class="font-weight-medium">
									<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="${requestScope.totalCart}"
										type="currency" />
								</h6>
							</div>
							<div class="d-flex justify-content-between">
								<h6 class="font-weight-medium">Shipping Fee</h6>
								<h6 class="font-weight-medium">
									<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="${requestScope.feeShip}"
										type="currency" />
								</h6>
							</div>
							<div class="d-flex justify-content-between">
								<h6 class="font-weight-medium">Membership Discount
									(${sessionScope.user.rankId.rank})</h6>
								<h6 class="font-weight-medium">
									<c:if test="${not empty user}">
										<fmt:formatNumber type="percent"
											value="${sessionScope.user.rankId.discount}" />
									</c:if>
									<c:if test="${empty user}">
										<fmt:formatNumber type="percent" value="0" />
									</c:if>
								</h6>
							</div>
							<div class="d-flex justify-content-between">
								<h6 class="font-weight-medium"></h6>
								<h6 class="font-weight-medium">
									<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="-${requestScope.totalCart + requestScope.feeShip - requestScope.totalPrice}"
										type="currency" />
								</h6>
							</div>
						</div>
						<div class="card-footer border-secondary bg-transparent">
							<div class="d-flex justify-content-between mt-2">
								<h5 class="font-weight-bold ">Total</h5>
								<h5 class="font-weight-bold ">
									<fmt:setLocale value="vi_VN" />
									<fmt:formatNumber value="${requestScope.totalPrice}"
										type="currency" />
								</h5>
							</div>
						</div>
					</div>
					<div class="card border-secondary mb-5">
						<div class="card-header bg-secondary border-0">
							<h4 class="font-weight-semi-bold m-0">Payment Method</h4>
						</div>
						<div class="card-body">
							<div class="form-group">
								<div class="custom-control custom-radio">
									<input type="radio" class="custom-control-input"
										checked="checked"> <label class="custom-control-label"
										for="COD">Cash On Delivery (COD)</label>
								</div>
							</div>
						</div>
						<div class="card-footer border-secondary bg-transparent">
							<c:if test="${sessionScope.cartQuantity==0}">
								<button
									class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Back To Shopping</button>
							</c:if>
							<c:if test="${sessionScope.cartQuantity>0}">
								<c:if test="${not empty user}">
									<button
										class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3">Place
										Order</button>
								</c:if>
								<c:if test="${empty user}">
									<button type="button"
										class="btn btn-lg btn-block btn-primary font-weight-bold my-3 py-3"
										data-toggle="modal" data-target="#loginModal">Login</button>
								</c:if>
							</c:if>
						</div>
	</form>
	</div>
	</div>
	</div>
	</div>
	<!-- Checkout End -->
	<script>
		var s_a = new Array();
		var state_arr = new Array("Hồ Chí Minh", "Hà Nội", "Hải Phòng",
				"Cần Thơ", "Đồng Nai", "Bắc Ninh", "Hải Dương", "Nghệ An",
				"Thừa Thiên-Huế", "Thanh Hóa", "Khánh Hòa", "Nam Định",
				"Đắk Lắk", "Thái Nguyên", "Bà Rịa-Vũng Tàu", "Cà Mau",
				"Bình Định", "Sóc Trăng", "An Giang", "Phú Thọ", "Thái Bình",
				"Quảng Ngãi", "Kiến Giang", "Bình Dương", "Phú Yên",
				"Bạc Liêu", "Bình Thuận", "Ninh Thuận", "Quảng Ninh",
				"Hà Tĩnh", "Quảng Bình", "Đồng Tháp", "Lạng Sơn", "Gia Lai",
				"Long An", "Trà Vinh", "Ninh Bình", "Tây Ninh", "Tiền Giang",
				"Hòa Bình", "Vĩnh Long", "Hậu Giang", "Yên Bái", "Lào Cai",
				"Bến Tre", "Bắc Giang", "Cao Bằng", "Hà Giang", "Tuyên Quang",
				"Bắc Kạn", "Sơn La", "Quảng Trị", "Đà Nẵng", "Hưng Yên",
				"Hà Nam", "Vĩnh Phúc", "Quảng Nam", "Điện Biên", "Bình Phước",
				"Lâm Đồng", "Lai Châu", "Kon Tum", "Đắk Nông");
		s_a[0] = " Thủ Đức | Quận Mười Một | Quận Năm | Quận Mười | Phú Nhuận | Quận Sáu | Quận Ba | Quận Một | Quận Bốn | Tân Phú | Bình Thạnh | Quận Bảy | Quận Hai | Hóc Môn | Quận Chín | Tân Túc | Nhà Bè | Củ Chi | Cần Giờ | Gò Vấp ";
		s_a[1] = " Sơn Tây | Đống Đa | Hai BàTrưng | Hoàn Kiếm | Cầu Giấy | Ba Đình | Thanh Xuân | Tây Hồ | Hà Đông | Cầu Diễn | Văn Điển | Trâu Quỳ | Trôi | Phùng | Đông Anh | Thường Tín | Phúc Thọ | Mê Linh | Liên Quan | Kim Bài | Chúc Sơn | Quốc Oai | Phú Xuyên | Vân Đình | Sóc Sơn | Đại Nghĩa | Tây Đằng ";
		s_a[2] = " An Dương | Núi Đèo | Núi Đối | An Lão | Vĩnh Bảo | Tiên Lãng | Cát Bà ";
		s_a[3] = " Bình Thủy | Cái Răng | Ô Môn | Thốt Nốt | Phong Điền | Thới Lai | Cờ Đỏ | Vĩnh Thạnh ";
		s_a[4] = " Trảng Bom | Long Khánh | Thống Nhất | Long Thành | Nhơn Trạch | Gia Ray | Tân Phú | Vĩnh An ";
		s_a[5] = " Hồ | Lim | Chờ | Thứa | Gia Bình | Phố Mới ";
		s_a[6] = " Lai Cách | Gia Lộc | Phú Thái | Kẻ Sặt | Ninh Giang | Kinh Môn | Thanh Miện | Thanh Hà | Tứ Kỳ | Nam Sách ";
		s_a[7] = " Diễn Châu | Hưng Nguyên | Cầu Giát | Đô Lương | Nam Đàn | Quán Hành | Yên Thành | Thanh Chương | Tân Kỳ | Anh Sơn | Quỳ Hợp | Quỳ Châu | Con Cuông | Mường Xén | Kim Sơn | Hòa Bình ";
		s_a[8] = " Phú Vang | Sịa | Hương Trà | Phú Lộc | Phong Điền | Khe Tre | A Lưới ";
		s_a[9] = " Hậu Lộc | Bút Sơn | Quảng Xương | Vạn Hà | Rừng Thông | Nga Sơn | Quán Lào | Thọ Xuân | Triệu Sơn | Nông Cống | Vĩnh Lộc | Tĩnh Gia | Ngọc Lặc | Kim Tân | Cẩm Thủy | Bến Sung | Cành Nàng | Yên Cát | Lang Chánh | Thường Xuân | Quan Hóa | Mường Lát | Quan Sơn ";
		s_a[10] = " Cam Ranh | Cam Ranh | Diên Khánh | Vạn Giã | Ninh Hòa | Cam Lâm | Tô Hạp | Khánh Vĩnh ";
		s_a[11] = " Xuân Trường | Cổ Lễ | Nam Giang | Yên Định | Ngô Đồng | Mỹ Lộc | Lâm | Gôi | Liễu Đề ";
		s_a[12] = " Phước An | Buôn Trấp | Quảng Phú | Krông Năng | Ea Kar | Ea Drăng | Krông Kmar | M’Đrăk | Lắk | Ea Súp ";
		s_a[13] = " Hương Sơn | Đu | Chùa Hang | Chợ Chu ";
		s_a[14] = " Thị Trấn Long Điền | Thị Trấn Phú Mỹ | Thị Trấn Đất Đỏ | Thị Trấn Ngải Giao | Thị Trấn Phước Bửu | Côn Đảo ";
		s_a[15] = " Cái Nước | Trần Văn Thời | Cái Đôi Vàm | Đầm Dơi | Thới Bình | Năm Căn | U Minh | Ngọc Hiển ";
		s_a[16] = " An Nhơn | Tuy Phước | Bồng Sơn | Phù Mỹ | Phù Cát | Phú Phong | Hoài Ân | Vĩnh Thạnh | An Lão | Vân Canh ";
		s_a[17] = " Kế Sách | Long Phú | Châu Thành | Mỹ Xuyên | Vĩnh Châu | Cù Lao Dung | Phú Lộc | Huỳnh Hữu Nghĩa ";
		s_a[18] = " Châu Đốc | Chợ Mới | Tân Châu | An Phú | Phú Mỹ | Cái Dầu | An Châu | Núi Sập | Nhà Bàng | Tri Tôn ";
		s_a[19] = " Lâm Thao | Phú Thọ | Thanh Thủy | Phong Châu | Thanh Ba | Sông Thao | Hưng Hóa | Đoan Hùng | Hạ Hòa | Thanh Sơn | Yên Lập ";
		s_a[20] = " Hưng Hà | Vũ Thư | Đông Hưng | Quỳnh Côi | Thanh Nê | Diêm Điền ";
		s_a[21] = " Lý Sơn | Tư Nghĩa | Mộ Đức | Sơn Tịnh | Nghĩa Hành | Đức Phổ | Bình Sơn | Sơn Hà | Minh Long | Trà Bồng | Tây Trà | Ba Tơ ";
		s_a[22] = " Phú Quốc | Hà Tiên | Minh Lương | Tân Hiệp | Giồng Riềng | Gò Quao | An Biên | Vĩnh Thuận | Thứ Mười Một | Hòn Đất | Kiên Lương ";
		s_a[23] = " Dĩ An | Lái Thiêu | Tân Uyên | Phước Vĩnh | Dầu Tiếng ";
		s_a[24] = " Chí Thạnh | Sông Cầu | Củng Sơn | Đồng Xuân | Hai Riêng ";
		s_a[25] = " Thị Trấn Giá Rai | Thị Trấn Hòa Bình | Thị Trấn Phước Long | Thị Trấn Gành Hào | Thị Trấn Ngan Dừa ";
		s_a[26] = " Phú Quý | Võ Xu | Liên Hương | Ma Lâm | Thuận Nam | Tánh Linh | Chợ Lầu ";
		s_a[27] = " Khánh Hải | Phước Dân | Tân Sơn | Bác Ái ";
		s_a[28] = " Cẩm Phả | Quảng Yên | Móng Cái | Quảng Hà | Cô Tô | Trới ";
		s_a[29] = " Đức Thọ | Nghi Xuân | Nghèn | Thạch Hà | Cẩm Xuyên | Kỳ Anh | Phố Châu | Hương Khê | Vũ Quang ";
		s_a[30] = " Ba Đồn | Kiến Giang | Hoàn Lão | Quán Hàu | Đồng Lê | Quy Đạt ";
		s_a[31] = " Sa Đéc | Lấp Vò | Lai Vung | Cái Tàu Hạ | Thanh Bình | Mỹ Thọ | Sa Rài | Tràm Chim ";
		s_a[32] = " Thị Trấn Cao Lộc | Hữu Lũng | Đồng Mỏ | Văn Quan | Bắc Sơn | Thị Trấn Na Sầm | Lộc Bình | Thị Trấn Thất Khê | Bình Gia | Đình Lập ";
		s_a[33] = " Chư Sê | A Yun Pa | Đăk Đoa | Chư Ty | Phú Hòa | Chư Prông | Ia Pa | Kon Dơng | Phú Túc | K Bang | Ia Kha | Kông Chro ";
		s_a[34] = " Cần Đước | Cần Giuộc | Tầm Vu | Tân Trụ | Bến Lức | Hậu Nghĩa | Thủ Thừa | Tân Thạnh | Đông Thành | Vĩnh Hưng | Thạnh Hóa | Tân Hưng ";
		s_a[35] = " Trà Cú | Cầu Kè | Tiểu Cần | Châu Thành | Duyên Hải ";
		s_a[36] = " Yên Ninh | Phát Diệm | Yên Thịnh | Me | Thiên Tồn | Tam Điệp | Nho Quan ";
		s_a[37] = " Hòa Thành | Gò Dầu | Trảng Bàng | Bến Cầu | Châu Thành | Dương Minh Châu | Tân Châu | Tân Biên ";
		s_a[38] = " Tân Hiệp | Chợ Gạo | Vĩnh Bình | Tân Hòa | Mỹ Phước ";
		s_a[39] = " Vụ Bản | Hàng Trạm | Bo | Lương Sơn | Kỳ Sơn | Chi Nê | Cao Phong | Mường Khến | Mai Châu | Đà Bắc ";
		s_a[40] = " Bình Minh | Long Hồ | Cái Nhum | Vũng Liêm | Trà Ôn | Tam Bình ";
		s_a[41] = " Ngã Bảy | Một Ngàn | Ngã Sáu | Nàng Mau | Phụng Hiệp | Long Mỹ ";
		s_a[42] = " Thị Trấn Yên Thế | Sơn Thịnh | Cổ Phúc | Mù Cang Chải | Thị Trấn Trạm Tấu ";
		s_a[43] = " Thị Trấn Phố Lu | Si Ma Cai | Thị Trấn Phố Ràng | Thị Trấn Mường Khương | Sa Pa | Bắc Hà | Bát Xát | Thị Trấn Khánh Yên ";
		s_a[44] = " Châu Thành | Chợ Lách | Mỏ Cày | Ba Tri | Giồng Trôm | Bình Đại | Thạnh Phú ";
		s_a[45] = " Thắng | Bích Động | Vôi | Cao Thượng | Neo | Đồi Ngô | Cầu Gồ | Chũ | An Châu ";
		s_a[46] = " Thị Trấn Quảng Uyên | Thị Trấn Trùng Khánh | Thị Trấn Nước Hai | Thị Trấn Tà Lùng | Thị Trấn Hùng Quốc | Thị Trấn Xuân Hoà | Thị Trấn Thông Nông | Pác Miầu | Tân Việt | Thị Trấn Thanh Nhật | Thị Trấn Bảo Lạc | Thị Trấn Nguyên Bình | Thị Trấn Đông Khê ";
		s_a[47] = " Thị Trấn Đồng Văn | Thị Trấn Mèo Vạc | Thị Trấn Yên Minh | Lũng Hồ | Thị Trấn Vĩnh Tuy | Thị Trấn Việt Quang | Thị Trấn Vinh Quang | Thị Trấn Tam Sơn | Yên Bình | Thị Trấn Việt Lâm | Thị Trấn Vị Xuyên | Yên Phú ";
		s_a[48] = " Sơn Dương | Thị Trấn Tân Yên | Thị Trấn Vĩnh Lộc | Huyện Chiêm Hóa | Thị Trấn Na Hang ";
		s_a[49] = " Chợ Mới | Chợ Rã | Bộc Bố | Phủ Thông | Bằng Lũng | Yến Lạc | Vân Tùng ";
		s_a[50] = " Hát Lót | Thị Trấn Thuận Châu | Phù Yên | Yên Châu | Sông Mã | Mộc Châu | Ít Ong | Mường Chiên | Bắc Yên | Sốp Cộp ";
		s_a[51] = " Quảng Trị | Ái Tử | Hải Lăng | Gio Linh | Hồ Xá | Cam Lộ | Khe Sanh | Krông Klang ";
		s_a[52] = " Thanh Khê | Cẩm Lệ | Sơn Trà | Ngũ Hành Sơn | Liên Chiểu | Hòa Vang ";
		s_a[53] = " Như Quỳnh | Yên Mỹ | Khoái Châu | Văn Giang | Bẩn Yên Nhân | Lương Bằng | Ân Thi | Vương | Trần Cao ";
		s_a[54] = " Vĩnh Trụ | Bình Mỹ | Hòa Mạc | Thanh Lưu | Quế ";
		s_a[55] = " Yên Lạc | Vĩnh Tường | Hợp Hòa | Phúc Yên | Hương Canh | Lập Thạch | Tam Đảo ";
		s_a[56] = " Quảng Hà | Hội An | Điện Bàn | Hà Lam | Duy Xuyên | Phú Ninh | Quế Sơn | Núi Thành | Đại Lộc | Tiên Phước | Hiệp Đức | Trà My | Prao | Khâm Đức | Thạnh Mỹ ";
		s_a[57] = " Mường Ảng | Thị Trấn Tủa Chùa | Thị Trấn Tuần Giáo | Điện Biên Đông | Mường Chà | Mường Nhé ";
		s_a[58] = " Bình Long | Chơn Thành | Bù Đốp | Lộc Ninh | Đức Phong | Tân Phú ";
		s_a[59] = " Ấp Đa Lợi | Bảo Lộc | Đưc Trọng | Thạnh Mỹ | Đồng Nai | Đinh Văn | Di Linh | Đạ Tẻh | Lộc Thắng | Ma Đa Gui | Lạc Dương ";
		s_a[60] = " Phong Thổ | Than Uyên | Tam Đường | Thị Trấn Sìn Hồ | Thị Trấn Mường Tè ";
		s_a[61] = " Mang La | Đắk Hà | Đắk Tô | Sa Thầy | Plei Kần | Đắk Glei | Đắk Rve | Kon Plông ";
		s_a[62] = " Đắk Mil | Ea T’ling | Kiến Đức | Đắk Mâm | Đắk Song ";

		function print_state() {
			var option_str = document.getElementById("state");
			option_str.length = 0;
			option_str.options[0] = new Option('Select City', '');
			option_str.selectedIndex = 0;
			for (var i = 0; i < state_arr.length; i++) {
				option_str.options[option_str.length] = new Option(
						state_arr[i], state_arr[i]);
			}
		}
		print_state();

		function print_distric(city_index) {

			var option_str = document.getElementById("distric");
			option_str.length = 0;
			option_str.options[0] = new Option('Select District', '');
			option_str.selectedIndex = 0;
			var distric_arr = s_a[city_index - 1].split("|");

			for (var i = 0; i < distric_arr.length; i++) {

				option_str.options[option_str.length] = new Option(
						distric_arr[i], distric_arr[i]);
			}
		}
	</script>




	<!-- Back to Top -->
	<a href="#" class="btn btn-primary back-to-top"><i
		class="fa fa-angle-double-up"></i></a>

</body>
