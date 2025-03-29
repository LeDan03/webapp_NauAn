
import 'package:cook_blog_app/utils/colors.dart';
import 'package:cook_blog_app/utils/functions/functions.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:iconsax/iconsax.dart';

class RegisterScreen extends StatelessWidget {
  // final AuthController authController = AuthController();

  final nameController = TextEditingController();
  final emailController = TextEditingController();
  final passwordController = TextEditingController();
  final confirmPasswordController = TextEditingController();
  final String role = 'admin';

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: const Text(
            'Đăng ký',
            style: TextStyle(color: Colors.white, fontSize: 30),
          ),
          backgroundColor: AppColors.primaryColor,
          centerTitle: true,
          iconTheme: const IconThemeData(color: Colors.white)),
      body: SingleChildScrollView(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          crossAxisAlignment: CrossAxisAlignment.center,
          children: [
            const SizedBox(height: 50),
            const Image(
              image: AssetImage('assets/images/long_logo.png'),
              width: 150,
              height: 150,
            ),
            const SizedBox(height: 30),
            Form(
              child: Padding(
                padding: const EdgeInsets.all(20),
                child: Column(
                  children: [
                    TextFormField(
                      controller: nameController,
                      decoration: const InputDecoration(
                        labelText: 'Họ và tên',
                        hintText: 'Nhập họ và tên',
                        prefixIcon: Icon(Icons.person),
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                        ),
                      ),
                      validator: (value) {
                        if (value == null || value.isEmpty) {
                          return 'Vui lòng nhập họ và tên';
                        }
                        return null;
                      },
                    ),
                    const SizedBox(height: 20),
                    TextFormField(
                      controller: emailController,
                      decoration: const InputDecoration(
                        labelText: 'Email',
                        hintText: 'Nhập email',
                        prefixIcon: Icon(Icons.email),
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                        ),
                      ),
                      validator: (value) {
                        return validateEmail(value);
                      },
                    ),
                    const SizedBox(height: 20),
                    TextFormField(
                      controller: passwordController,
                      obscureText: true,
                      decoration: const InputDecoration(
                        labelText: 'Mật khẩu',
                        hintText: 'Nhập mật khẩu',
                        prefixIcon: Icon(Icons.lock),
                        suffixIcon: Icon(Iconsax.eye_slash),
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                        ),
                      ),
                      validator: (value) {
                        if (value == null || value.isEmpty) {
                          return 'Vui lòng nhập mật khẩu';
                        }
                        return null;
                      },
                    ),
                    const SizedBox(height: 20),
                    TextFormField(
                      controller: confirmPasswordController,
                      obscureText: true,
                      decoration: const InputDecoration(
                        labelText: 'Nhập lại mật khẩu',
                        hintText: 'Nhập lại mật khẩu',
                        prefixIcon: Icon(Iconsax.password_check),
                        suffixIcon: Icon(Iconsax.eye_slash),
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                        ),
                      ),
                      validator: (value) {
                        if (value != passwordController.text) {
                          return 'Mật khẩu không khớp';
                        }
                        return null;
                      },
                    ),
                    const SizedBox(height: 30),
                    // Obx(
                    //   () => ElevatedButton(
                    //     onPressed: authController.isLoading.value
                    //         ? null
                    //         : () {
                    //             if (passwordController.text ==
                    //                 confirmPasswordController.text) {
                    //               authController.signUp(
                    //                 emailController.text.trim(),
                    //                 passwordController.text.trim(),
                    //                 nameController.text.trim(),
                    //                 role,
                    //               );
                    //             } else {
                    //               Get.snackbar(
                    //                 'Lỗi',
                    //                 'Mật khẩu không khớp',
                    //                 snackPosition: SnackPosition.BOTTOM,
                    //               );
                    //             }
                    //           },
                    //     style: ElevatedButton.styleFrom(
                    //       backgroundColor: AppColors.light,
                    //       foregroundColor: AppColors.secondary,
                    //       minimumSize: const Size(double.infinity, 50),
                    //       shape: RoundedRectangleBorder(
                    //         borderRadius: BorderRadius.circular(10),
                    //       ),
                    //     ),
                    //     child: const Text('Đăng ký'),
                    //   ),
                    // )
                    ElevatedButton(onPressed: (){}, child: const Text('Đăng ký'))
                  ],
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
