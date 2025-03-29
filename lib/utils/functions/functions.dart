// import 'package:intl/intl.dart';

String? validateEmail(String? value) {
  if (value == null || value.isEmpty) {
    return 'Vui lòng nhập địa chỉ email';
  }

  // Regular expression for validating an email
  String pattern = r'^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$';
  RegExp regExp = RegExp(pattern);

  if (!regExp.hasMatch(value)) {
    return 'Địa chỉ email không hợp lệ';
  }

  return null;
}

String? validatePassword(String? value) {
  if (value == null || value.isEmpty) {
    return 'Vui lòng nhập mật khẩu';
  }

  // Regular expression for validating password
  String pattern =
      r'^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[!@#\$%^&*()_+\-=\[\]{};:"\\|,.<>\/?]).{8,}$';
  RegExp regExp = RegExp(pattern);

  if (!regExp.hasMatch(value)) {
    return 'Mât khẩu phải chứa ít nhất 8 ký tự, bao gồm chữ hoa, chữ thường, số và ký tự đặc biệt';
  }

  return null;
}

// String currencyFormat(double value) {
//   final NumberFormat formatter = NumberFormat.currency(
//     locale: 'vi_VN',
//     symbol: 'đ',
//     decimalDigits: 0,
//   );
//   return formatter.format(value);
// }
