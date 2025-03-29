
import 'package:cook_blog_app/screens/register_screen.dart';
import 'package:cook_blog_app/utils/colors.dart';
import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:iconsax/iconsax.dart';


class LoginScreen extends StatelessWidget {
  // final AuthController authController = Get.find<AuthController>();

  final emailController = TextEditingController();
  final passwordController = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Đăng nhập',
          style: TextStyle(
              color: Colors.white,
              fontSize: 30,
              fontWeight: FontWeight.bold),
        ),
        backgroundColor: AppColors.primaryColor,
        centerTitle: true,
        automaticallyImplyLeading: false,
      ),
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
                      controller: emailController,
                      decoration: const InputDecoration(
                        labelText: 'Email',
                        hintText: 'Nhập email',
                        prefixIcon: Icon(Icons.email),
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                        ),
                      ),
                    ),
                    const SizedBox(height: 20),
                    TextFormField(
                      obscureText: true,
                      controller: passwordController,
                      decoration: const InputDecoration(
                        labelText: 'Mật khẩu',
                        hintText: 'Nhập mật khẩu',
                        prefixIcon: Icon(Icons.lock),
                        suffixIcon: Icon(Iconsax.eye_slash),
                        border: OutlineInputBorder(
                          borderRadius: BorderRadius.all(Radius.circular(10)),
                        ),
                      ),
                    ),
                    const SizedBox(height: 7),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.spaceBetween,
                      children: [
                        Row(
                          children: [
                            Checkbox(value: true, onChanged: (value) {}),
                            const Text('Lưu mật khẩu'),
                          ],
                        ),
                        TextButton(
                          onPressed: () {
                            Get.toNamed('/forgot-password');
                          },
                          child: const Text(
                            'Quên mật khẩu?',
                            style: TextStyle(color: Colors.blue),
                          ),
                        ),
                      ],
                    ),
                    const SizedBox(height: 20),
                    // Obx(
                    //   () => ElevatedButton(
                    //     onPressed: () {
                    //       authController.login(
                    //           emailController.text, passwordController.text);
                    //     },style: ElevatedButton.styleFrom(
                    //       backgroundColor: AppColors.light,
                    //       foregroundColor: AppColors.secondary
                    //     ),
                    //     child: authController.isLoading.value
                    //         ? const CircularProgressIndicator(
                    //             valueColor:
                    //                 AlwaysStoppedAnimation<Color>(Colors.white),
                    //           )
                    //         : const Text('Đăng nhập'),
                    //   ),
                    // ),
                    ElevatedButton(onPressed: (){}, child: const Text('Đăng nhập')),
                    const SizedBox(height: 5),
                    Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        const Text('Chưa có tài khoản?'),
                        TextButton(
                          onPressed: () {
                            Get.to(() => RegisterScreen());
                          },
                          child: const Text('Đăng ký ngay',
                              style: TextStyle(color: Colors.blue)),
                        ),
                      ],
                    ),
                    const Row(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        Flexible(
                            child: Divider(
                          color: Colors.black,
                          thickness: 0.5,
                          indent: 60,
                          endIndent: 5,
                        )),
                        Text('Hoặc', style: TextStyle(color: Colors.black)),
                        Flexible(
                            child: Divider(
                          color: Colors.black,
                          thickness: 0.5,
                          indent: 5,
                          endIndent: 60,
                        )),
                      ],
                    ),
                    const SizedBox(height: 5),
                    Container(
                      decoration: BoxDecoration(
                        color: Colors.transparent,
                        borderRadius: BorderRadius.circular(50),
                      ),
                      child: IconButton(
                        onPressed: () {},
                        icon: const Image(
                          image: AssetImage('assets/images/google.png'),
                          height: 30,
                        ),
                      ),
                    ),
                  ],
                ),
              ),
            )
          ],
        ),
      ),
    );
  }
}
