package org.acm.store.controller;

import org.acm.store.model.cart.Cart;
import org.acm.store.model.DataBase;
import org.acm.store.model.user.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Created by SM2A
 * Seyed Mohammad Amin Atyabi
 */


@Validated
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class Store {

    @Autowired
    CustomerService customerService;
    
    private final DataBase dataBase;

    public Store(DataBase dataBase) {
        this.dataBase = dataBase;
    }

    @GetMapping("/")
    public String/*List<ArrayList<Product>>*/ homePage() {
//        if (!Validation.isTaken("admin@admin.com", "007"))
            dataBase.addAdmin("admin", "admin", "admin", "admin@admin.com", "007", "admin");
//        return dataBase.getMainProducts();
        return "Home page";
    }

    @GetMapping("/test")
    public String/*List<ArrayList<Product>>*/ test() {

        dataBase.addCategory("KEYBOARDTWO");
        dataBase.addCategory("KEYBOARDTHREE");
        dataBase.addCategory("KEYBOARDFOUR");

        dataBase.addProduct("MX KKKeys 001", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX KKKeys 67", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX KKKeys 42", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX KKKeys 56", "Logitech", 5, 100, "KEYBOARDTWO",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");


        dataBase.addProduct("MX Keyss88", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX Keysss 0987", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX Keyss 45", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MX Keysss 2345 iui", "Logitech", 5, 100, "KEYBOARDTHREE",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");

        dataBase.addProduct("MMX Keys ytf", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MXXX Keys tyf", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MXX Keys 56 err", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");
        dataBase.addProduct("MMX Keys fdzdx", "Logitech", 5, 100, "KEYBOARDFOUR",
                "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRQglsve8y_nPAIkhWk6s9M1aywnIUevqxjEw&usqp=CAU");

//        return dataBase.getMainProducts();
        return "OK";
    }

    /*@GetMapping("/signup")
    public String signupPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "signup page";
    }*/

    @GetMapping("/test1")
    public String test1(){

        dataBase.addCostumer("Amin","Atyabi","123","amin@amin.net","123654789","Tehran");
        dataBase.addCostumer("Paria","Khoshtab","123","test@test.net","987456321","Tehran");
        dataBase.addCostumer("Parnian","Fazel","123","salam@test.net","147852369","Tehran");

        dataBase.addCategory("game");
        dataBase.addCategory("mouse");
        dataBase.addCategory("keyboard");

        dataBase.addProduct("MFSX", "Microsoft", 5, 60, "GAME",
                "https://i1.sndcdn.com/artworks-000538033689-8y3q0k-t500x500.jpg");
        dataBase.addProduct("Death Adder", "Razer", 5, 30, "MOUSE",
                "https://hardwaremarket.net/wp-content/uploads/2021/03/razer-deathadder-essential-optical-gaming-mouse-white-1571981760387.jpg");
        dataBase.addProduct("MX Keys", "Logitech", 5, 100, "KEYBOARD",
                "https://www.logitech.com/content/dam/logitech/en/products/keyboards/mx-keys/gallery/us-mx-keys-gallery-graphite-front.png");

        Cart cart = dataBase.findOpenCartByUser(2);

        dataBase.addItem(cart.getID(), 13);
        dataBase.addItem(cart.getID(), 13);
        dataBase.addItem(cart.getID(), 13);
        dataBase.addItem(cart.getID(), 14);
        dataBase.addItem(cart.getID(), 14);
        dataBase.addItem(cart.getID(), 15);

        dataBase.addComment(3,13,"wowwww");
        dataBase.addComment(4,13,"greattttt!!!!");

        dataBase.addComment(5,14,"wowwww");
        dataBase.addComment(2,15,"greattttt!!!!");

        dataBase.addCredit(3, 10000);
        dataBase.addCredit(4, 10000);
        dataBase.addCredit(5, 10000);

        dataBase.addCredit(2, 10000);
//        dataBase.purchase(2);

        cart = dataBase.findOpenCartByUser(2);

        dataBase.addItem(cart.getID(), 1);
        dataBase.addItem(cart.getID(), 2);
        
        dataBase.addRatingToProduct(13,5);
        dataBase.addRatingToProduct(13,5);
        dataBase.addRatingToProduct(13,2);
        dataBase.addRatingToProduct(13,1);
        dataBase.addRatingToProduct(13,3);

        dataBase.addRatingToProduct(14,4);
        dataBase.addRatingToProduct(14,5);
        dataBase.addRatingToProduct(14,2);
        dataBase.addRatingToProduct(14,1);
        dataBase.addRatingToProduct(14,3);

        dataBase.addRatingToProduct(15,1);
        dataBase.addRatingToProduct(15,5);
        dataBase.addRatingToProduct(15,2);
        dataBase.addRatingToProduct(15,1);
        dataBase.addRatingToProduct(15,3);

        return "Test 1";
    }

    /*@PostMapping("/signup")
    public String signup(@RequestParam(required = false) @NotBlank @Valid String firstName,
                         @RequestParam(required = false) @NotBlank @Valid String lastName,
                         @RequestParam(required = false) @NotBlank @Valid String password,
                         @RequestParam(required = false) @NotBlank @Valid String email,
                         @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                         @RequestParam(required = false) @NotBlank @Valid String address) throws JSONException {
        DataBase dataBase = dataBase;
        if (Validation.isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        dataBase.addCostumer(firstName, lastName, password, email, phoneNumber, address);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 1);
        jsonObject.put("ID", dataBase.validateUserByID(email, password));
        jsonObject.put("email", email);
        jsonObject.put("password", password);

        return jsonObject.toString();
    }*/

    /*@GetMapping("/login")
    public String loginPage(HttpServletRequest request) {
        if (Authentication.isLogin(request)) throw new CustomException("please logout first");
        return "login page";
    }*/

    /*@PostMapping("/login")
    public String login(@RequestParam(required = false) @NotBlank @Valid String password,
                        @RequestParam(required = false) @NotBlank @Valid String email) throws JSONException {
        DataBase dataBase = dataBase;
        long ID = dataBase.validateUserByID(email, password);
        if (ID != -1) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("code", 1);
            jsonObject.put("ID", dataBase.validateUserByID(email, password));
            jsonObject.put("email", email);
            jsonObject.put("password", password);
            return jsonObject.toString();
        } else throw new CustomException("email or password in correct");
    }*/

    /*@GetMapping("/logout")
    public String logout(HttpServletResponse response, HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        User user = Authentication.loggedInUser(request);
        Authentication.logout(response);
        return "goodbye " + user.getFirstName();
    }*/

    /*@PostMapping("/valid_login")
    public String validLogin(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email) {
        if (Authentication.loggedInUser(email, password) != null) return "1";
        else return "0";
    }*/

    /*@PostMapping("/valid_admin")
    public String validAdmin(@RequestParam(required = false) @NotBlank @Valid String password,
                             @RequestParam(required = false) @NotBlank @Valid String email) {
        User user = Authentication.loggedInUser(email, password);
        if (Authentication.isAdmin(user)) return "1";
        else return "0";
    }*/

    /*@GetMapping("/add/admin")
    public String addAdminPage(HttpServletRequest request) {
        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
            throw new CustomException("You dont have permission");
        return "add admin page";
    }*/

    /*@PostMapping("/admin/add")
    public String addAdmin(@RequestParam(required = false) @NotBlank @Valid String firstName,
                           @RequestParam(required = false) @NotBlank @Valid String lastName,
                           @RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email,
                           @RequestParam(required = false) @NotBlank @Valid String phoneNumber,
                           @RequestParam(required = false) @NotBlank @Valid String address) {
//        if (!Authentication.isLogin(request)) throw new CustomException("please login first");
//        if (!Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("You dont have permission");
        if (Validation.isTaken(email, phoneNumber)) throw new CustomException("This email or phone-number is taken");
        DataBase dataBase = dataBase;
        dataBase.addAdmin(firstName, lastName, password, email, phoneNumber, address);
        return String.valueOf(dataBase.validateUserByID(email, password));
    }*/

    /*@PostMapping("/purchase")
    public String purchase(@RequestParam(required = false) @NotBlank @Valid String password,
                           @RequestParam(required = false) @NotBlank @Valid String email) {
//        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(email,password);
        dataBase.purchase(user.getID());
        return "Thank you for your order";
    }*/

    /*@PostMapping("/credit/add")
    public String addCredit(@RequestParam(required = false) @NotBlank @Valid String password,
                            @RequestParam(required = false) @NotBlank @Valid String email,
                            @RequestParam(required = false) @NotBlank @Valid String amount) {
//        if (!Authentication.isLogin(request)) throw new CustomException("Please login first");
//        if (Authentication.isAdmin(Authentication.loggedInUser(request)))
//            throw new CustomException("Make sure you are a costumer");
        User user = Authentication.loggedInUser(email, password);
        dataBase.addCredit(user.getID(), Long.parseLong(amount));
        return "Successful payment";
    }*/
}
