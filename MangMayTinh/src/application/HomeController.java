package application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import core.*;

public class HomeController {
	@FXML
	TextField txtip1;
	@FXML
	TextField txtip2;
	@FXML
	TextField txtip3;
	@FXML
	TextField txtip4;
	@FXML
	TextField txthost;
	@FXML
	Label thongBao;
	@FXML
	TextField txtSubnet;
	@FXML
	TextField txtWildcard;
	@FXML
	TextField txtAdd;
	@FXML
	TextField txtBroad;
	@FXML
	TextField txtHosts;
	@FXML
	TextField txtpvIP;
	ListPrefix vPrefix = new ListPrefix();
	
	public void clickTinh() {
		try {
			kiemTraMang();
			int host = Integer.parseInt(txthost.getText());
			kiemTraIP(host);
			txtSubnet.setText(sub);
			txtAdd.setText(addr);
			txtWildcard.setText(wildcard);
			txtBroad.setText(broad);
			txtpvIP.setText(pvIP);
			txtHosts.setText(numHost);
			luuAdd = txtAdd.getText();
			luuBroad = txtBroad.getText();
			luuSubnet = txtSubnet.getText();
			luuWild = txtWildcard.getText();

		} catch (Exception e) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText("Bạn chưa nhập đầy đủ thông tin,hoặc IP không phải là các số");
			alert.showAndWait();
		}
	}

	public void kiemTraMang() {
		int ip1, ip2, ip3, ip4;
		ip1 = Integer.parseInt(txtip1.getText());
		ip2 = Integer.parseInt(txtip2.getText());
		ip3 = Integer.parseInt(txtip3.getText());
		ip4 = Integer.parseInt(txtip4.getText());
		if (ip1 < 1 || ip1 > 223) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText("Phải nhập số đầu tiên lớn hơn bằng 1 và nhỏ hơn bằng 223");
			alert.showAndWait();
		} else if (ip2 < 0 || ip2 > 255) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText("nhập sai ô thứ 2");
			alert.showAndWait();
		} else if (ip3 < 0 || ip3 > 255) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText("nhập sai ô thứ 3");
			alert.showAndWait();
		} else if (ip4 < 0 || ip4 > 255) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText("nhập sai ô thứ 4");
			alert.showAndWait();
		} else {
			if (ip1 >= 1 && ip1 <= 126)// Kiểm tra thuộc lớp mạng nào?
			{

				thongBao.setText("MẠNG LỚP A");

			} else if (ip1 >= 128 && ip1 <= 191) {

				thongBao.setText("MẠNG LỚP B");

			} else if (ip1 >= 192 && ip1 <= 223) {

				thongBao.setText("MẠNG LỚP C");

			}

		}
	}

	String sub, addr, broad, pvIP, numHost, wildcard;

	public void kiemTraIP(int hostN) {

		int ip2, ip3, ip4, host, k;
		ip2 = Integer.parseInt(txtip2.getText());
		ip3 = Integer.parseInt(txtip3.getText());
		ip4 = Integer.parseInt(txtip4.getText());
		host = Integer.parseInt(txthost.getText());
		if (vPrefix.checkPrefix(host)) {
			k = 32 - hostN;
			numHost = "2^" + String.valueOf(k) + " - 2 ";
			if (host == 8) {
				sub = "255.0.0.0";
				addr = txtip1.getText() + ".0.0.0";
				wildcard = "0.255.255.255";
				broad = txtip1.getText() + ".255.255.255";
				pvIP = txtip1.getText() + ".0.0.1/" + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ ".255.255.254/" + String.valueOf(host);
			} else if (host == 16) {
				sub = "255.255.0.0";
				addr = txtip1.getText() + '.' + txtip2.getText() + ".0.0";
				wildcard = "0.0.255.255";
				broad = txtip1.getText() + '.' + txtip2.getText() + ".255.255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + txtip2.getText() + ".255.254/" + String.valueOf(host);
			} else if (host == 24) {
				sub = "255.255.255.0";
				wildcard = "0.0.0.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + ".1/" + String.valueOf(host)
						+ " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + ".254/"
						+ String.valueOf(host);
			} else if (host == 9) {
				sub = "255.128.0.0";
				wildcard = "0.127.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 128) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 127) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 128) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 127) + ".255.254/" + String.valueOf(host);
			} else if (host == 10) {
				sub = "255.192.0.0";
				wildcard = "0.63.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 192) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 63) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 192) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 63) + ".255.254/" + String.valueOf(host);
			} else if (host == 11) {
				sub = "255.224.0.0";
				wildcard = "0.31.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 224) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 31) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 224) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 31) + ".255.254/" + String.valueOf(host);
			} else if (host == 12) {
				sub = "255.240.0.0";
				wildcard = "0.15.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 240) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 15) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 240) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 15) + ".255.254/" + String.valueOf(host);
			} else if (host == 13) {
				sub = "255.248.0.0";
				wildcard = "0.7.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 248) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 7) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 248) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 7) + ".255.254/" + String.valueOf(host);
			} else if (host == 14) {
				sub = "255.252.0.0";
				wildcard = "0.3.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 252) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 3) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 252) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 3) + ".255.254/" + String.valueOf(host);
			} else if (host == 15) {
				sub = "255.254.0.0";
				wildcard = "0.1.255.255";
				addr = txtip1.getText() + '.' + String.valueOf(ip2 & 254) + ".0.0";
				broad = txtip1.getText() + '.' + String.valueOf(ip2 | 1) + ".255.255";
				pvIP = txtip1.getText() + '.' + String.valueOf(ip2 & 254) + ".0.1/" + String.valueOf(host) + " ĐẾN "
						+ txtip1.getText() + '.' + String.valueOf(ip2 | 1) + ".255.254/" + String.valueOf(host);
			} else if (host == 17) {
				sub = "255.255.128.0";
				wildcard = "0.0.127.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 128) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 127) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 128) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 127) + ".254/" + String.valueOf(host);
			} else if (host == 18) {
				sub = "255.255.192.0";
				wildcard = "0.0.63.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 192) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 63) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 192) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 63) + ".254/" + String.valueOf(host);
			} else if (host == 19) {
				sub = "255.255.224.0";
				wildcard = "0.0.31.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 224) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 31) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 224) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 31) + ".254/" + String.valueOf(host);
			} else if (host == 20) {
				sub = "255.255.240.0";
				wildcard = "0.0.15.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 240) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 15) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 240) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 15) + ".254/" + String.valueOf(host);
			} else if (host == 21) {
				sub = "255.255.248.0";
				wildcard = "0.0.7.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 248) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 7) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 248) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 7) + ".254/" + String.valueOf(host);
			} else if (host == 22) {
				sub = "255.255.252.0";
				wildcard = "0.0.3.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 252) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 3) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 252) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 3) + ".254/" + String.valueOf(host);
			} else if (host == 23) {
				sub = "255.255.254.0";
				wildcard = "0.0.1.255";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 254) + ".0";
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 | 1) + ".255";
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + String.valueOf(ip3 & 254) + ".1/"
						+ String.valueOf(host) + " ĐẾN " + txtip1.getText() + '.' + txtip2.getText() + '.'
						+ String.valueOf(ip3 | 1) + ".254/" + String.valueOf(host);
			} else if (host == 25) {
				sub = "255.255.255.128";
				wildcard = "0.0.0.127";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 128);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 127);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 128) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 127) - 1) + '/'
						+ String.valueOf(host);
			} else if (host == 26) {
				sub = "255.255.255.192";
				wildcard = "0.0.0.63";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 192);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 63);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 192) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 63) - 1) + '/'
						+ String.valueOf(host);
			} else if (host == 27) {
				sub = "255.255.255.224";
				wildcard = "0.0.0.31";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 224);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 31);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 224) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 31) - 1) + '/'
						+ String.valueOf(host);
			} else if (host == 28) {
				sub = "255.255.255.240";
				wildcard = "0.0.0.15";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 240);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 15);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 240) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 15) - 1) + '/'
						+ String.valueOf(host);
			} else if (host == 29) {
				sub = "255.255.255.248";
				wildcard = "0.0.0.7";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 248);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 7);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 248) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 7) - 1) + '/'
						+ String.valueOf(host);
			} else if (host == 30) {
				sub = "255.255.255.252";
				wildcard = "0.0.0.3";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 252);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 3);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 252) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 3) - 1) + '/'
						+ String.valueOf(host);
			} else if (host == 31) {
				sub = "255.255.255.254";
				wildcard = "0.0.0.1";
				addr = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 & 254);
				broad = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf(ip4 | 1);
				pvIP = txtip1.getText() + '.' + txtip2.getText() + '.' + txtip3.getText() + '.'
						+ String.valueOf((ip4 & 254) + 1) + '/' + String.valueOf(host) + " ĐẾN " + txtip1.getText()
						+ '.' + txtip2.getText() + '.' + txtip3.getText() + '.' + String.valueOf((ip4 | 1) - 1) + '/'
						+ String.valueOf(host);
			}

		} else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("WARNING");
			alert.setHeaderText(null);
			alert.setContentText(
					"Số Prefix nhập sai Bạn vui lòng xem lại số host! Phải nhỏ hơn 32 và lớn hơn 8");
			alert.showAndWait();
			txthost.setText(null);
		}
	}

	public void clickClear() {
		txtip1.setText(null);
		txtip2.setText(null);
		txtip3.setText(null);
		txtip4.setText(null);
		txthost.setText(null);
		txtSubnet.setText(null);
		txtAdd.setText(null);
		txtWildcard.setText(null);
		txtBroad.setText(null);
		txtHosts.setText(null);
		txtpvIP.setText(null);
		thongBao.setText("Đã xóa xong");
	}

	String luuSubnet, luuAdd, luuWild, luuBroad;

	// hàm chuyển qua nhị phân
	String ip;

	public void convertBinary(String chuoiGoc) {
		String[] result = chuoiGoc.split("[.]");
		int ip1, ip2, ip3, ip4;
		ip1 = Integer.parseInt(result[0]);
		ip2 = Integer.parseInt(result[1]);
		ip3 = Integer.parseInt(result[2]);
		ip4 = Integer.parseInt(result[3]);

		String num1 = Integer.toBinaryString(ip1);
		String num2 = Integer.toBinaryString(ip2);
		String num3 = Integer.toBinaryString(ip3);
		String num4 = Integer.toBinaryString(ip4);
		if (num1.length() < 8) {
			while (num1.length() < 8) {
				num1 = '0' + num1;
				if (num1.length() == 8) {
					break;
				}
			}
		}
		if (num2.length() < 8) {
			while (num2.length() < 8) {
				num2 = '0' + num2;
				if (num2.length() == 8) {
					break;
				}
			}

		}
		if (num3.length() < 8) {
			while (num3.length() < 8) {
				num3 = '0' + num3;
				if (num3.length() == 8) {
					break;
				}
			}
		}
		if (num4.length() < 8) {
			while (num4.length() < 8) {
				num4 = '0' + num4;
				if (num4.length() == 8) {
					break;
				}
			}
		}
		ip = num1 + "." + num2 + "." + num3 + "." + num4;// chuỗi nhị phân nhận được

	}

	int v1 = 0, v2 = 0, v3 = 0, v4 = 0;

	public void clickDecToBin() {
		clickAdd();
		clickSubnet();
		clickBroad();
		clickWildcard();
	}

	public void clickSubnet() {
		v1 = ~v1;// đảo trạng thái
		if (v1 == 0) {
			txtSubnet.setText(luuSubnet);// lấy lại giá trị đã lưu
		} else {// chuyển đổi
			try {
				if (txtSubnet.getText() != "") {
					convertBinary(txtSubnet.getText());
					txtSubnet.setText(ip);
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setHeaderText(null);
				alert.setContentText("Không có gì để chuyển đổi");
				alert.showAndWait();
			}
		}
	}

	public void clickWildcard() {
		v4 = ~v4;
		if (v4 == 0) {
			txtWildcard.setText(luuWild);
		} else {
			try {
				if (txtWildcard.getText() != "") {
					convertBinary(txtWildcard.getText());
					txtWildcard.setText(ip);
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setHeaderText(null);
				alert.setContentText("Không có gì để chuyển đổi");
				alert.showAndWait();
			}
		}
	}

	public void clickAdd() {
		v2 = ~v2;
		if (v2 == 0) {
			txtAdd.setText(luuAdd);
		} else {
			try {
				if (txtAdd.getText() != "") {
					convertBinary(txtAdd.getText());
					txtAdd.setText(ip);
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setHeaderText(null);
				alert.setContentText("Không có gì để chuyển đổi");
				alert.showAndWait();
			}
		}
	}

	public void clickBroad() {
		v3 = ~v3;
		if (v3 == 0) {
			txtBroad.setText(luuBroad);
		} else {
			try {
				if (txtBroad.getText() != "") {
					convertBinary(txtBroad.getText());
					txtBroad.setText(ip);
				}
			} catch (Exception e) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("WARNING");
				alert.setHeaderText(null);
				alert.setContentText("Không có gì để chuyển đổi");
				alert.showAndWait();
			}
		}
	}

}
