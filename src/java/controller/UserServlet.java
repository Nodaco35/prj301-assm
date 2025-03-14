

//Phan xem Record
package controller;

import dao.InspectionRecordDAO;
import dao.InspectionRequestDAO;
import dao.InspectionStationDAO;
import dao.NotificationDAO;
import dao.VehicleDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.InspectionRequest;
import model.InspectionStation;
import model.Notification;
import model.ShowRecord;
import model.User;
import model.Vehicle;

/**
 *
 * @author NC PC
 */
public class UserServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UserServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String action = (String) request.getParameter("action");
        User user = (User) session.getAttribute("user");
        //Phan quyen
        if (user != null && action == null && user.getRole().equals("Khách hàng")) {
            request.getRequestDispatcher("view/lobby.jsp").forward(request, response);
        } else if (user != null && action == null && user.getRole().equals("Quản trị viên")) {
            //Chuyen trang JSP
            out.print("Xin chào Admin");
        } else if (user != null && action == null && user.getRole().equals("CSGT")) {
            //Chuyen trang JSP
            out.print("Xin chào CSGT");
        } else if (user != null && action == null && user.getRole().equals("Cơ sở kiểm định")) {
            //Chuyen trang JSP
            out.print("Xin chào Cơ sở kiểm định");
        } else if (action == null) {
        } //Chuc nang cua Owner
        else if (action.equals("1") && user != null) {
            request.getRequestDispatcher("view/addVehicle.jsp").forward(request, response);
        } else if (action.equals("2") && user != null) {
            ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());
            request.setAttribute("vehicleList", vehicles);
            request.getRequestDispatcher("view/vehicleList.jsp").forward(request, response);
        } else if (action.equals("3") && user != null) {
            ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());
            if (vehicles != null) {
                request.setAttribute("vehicleList", vehicles);
                ArrayList<InspectionStation> stationList = InspectionStationDAO.getAllStations();
                request.setAttribute("stationList", stationList);
                request.getRequestDispatcher("view/registerForTest.jsp").forward(request, response);
            } else {
                out.print("VehicleDAO.getAllVehicleByUserID doesn't run");
            }
        } else if (action.equals("4") && user != null) {
            ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());
            request.setAttribute("vehicleList", vehicles);
            request.getRequestDispatcher("view/searchRecordByPlateNum.jsp").forward(request, response);
        } else if (action.equals("9") && user != null) {
            ArrayList<InspectionRequest> requests = InspectionRequestDAO.getInspectionRequestsByUser(user.getUserId());
            ArrayList<InspectionStation> stations = InspectionStationDAO.getAllStations();
            ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());

            request.setAttribute("requests", requests);
            request.setAttribute("stations", stations);
            request.setAttribute("vehicles", vehicles);
            for (InspectionRequest req : requests) {
                out.print(req);
                out.print("\n");
            }

            request.getRequestDispatcher("view/user_inspection_requests.jsp").forward(request, response);
        }else if ("100".equals(action) && user!=null) {
            ArrayList<Notification> notifications = NotificationDAO.getAllNotificationsByUserId(user.getUserId()); // Đã sửa
            request.setAttribute("notifications",
                    notifications != null ? notifications : new ArrayList<Notification>());
                    request.getRequestDispatcher("view/NotificationTable.jsp").forward(request, response);

        }//Chuc nang Owner Done -> Chay ham DAO
        else if (action.equals("1Done") && user != null) {
            String plateNumber = request.getParameter("plateNumber");
            String brand = request.getParameter("brand");
            String model = request.getParameter("model");
            String yearStr = request.getParameter("year");
            String engineNumber = request.getParameter("engineNumber");
            // Kiểm tra dữ liệu (tuỳ chọn)
            if (plateNumber == null || plateNumber.isEmpty()
                    || brand == null || brand.isEmpty()
                    || model == null || model.isEmpty()
                    || engineNumber == null || engineNumber.isEmpty()) {

                response.sendRedirect("addVehicle.jsp?error=missingData");
                return;
            }
            int manufactureYear;
            try {
                manufactureYear = Integer.parseInt(yearStr);
            } catch (NumberFormatException e) {
                response.sendRedirect("addVehicle.jsp?error=invalidYear");
                return;
            }

            Vehicle vehicle = new Vehicle(plateNumber, brand, model, manufactureYear, engineNumber);
            vehicle.setOwnerId(user.getUserId());
            vehicle = VehicleDAO.addVehicle(vehicle);
            if (vehicle != null) {
                ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());
                request.setAttribute("vehicleList", vehicles);
                request.getRequestDispatcher("view/vehicleList.jsp").forward(request, response);
            }

        } else if (action.equals("4Done")) {
            String plateNum = request.getParameter("plateNum");
            ArrayList<ShowRecord> records = InspectionRecordDAO.getRecordByPlateNumber(plateNum);

            request.setAttribute("record", records);
            request.getRequestDispatcher("view/recordList.jsp").forward(request, response);

        } else if (action.equals("3Done") && user != null) {
            String plateNumber = request.getParameter("vehicle");
            if (plateNumber == null || plateNumber.isEmpty()) {
                ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());
                request.setAttribute("vehicleList", vehicles);
                request.getRequestDispatcher("view/registerForTest.jsp?error=missingData").forward(request, response);
                return;
            }

            Vehicle vehicle = VehicleDAO.getVehicleByPlateNumber(plateNumber);
            if (vehicle == null) {
                ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());
                request.setAttribute("vehicleList", vehicles);
                request.getRequestDispatcher("view/registerForTest.jsp?error=invalidVehicle").forward(request, response);

                return;
            } else {
                InspectionRequest iRequest = new InspectionRequest();
                int station = Integer.parseInt((String) request.getParameter("station"));
                iRequest.setVehicleId(vehicle.getVehicleId());
                iRequest.setStationId(station);
                iRequest = InspectionRequestDAO.addInspectionRequest(iRequest);

                if (iRequest != null) {
                    //Thêm Request thành công -> Chuyển hướng đến view
                    ArrayList<InspectionRequest> requests = InspectionRequestDAO.getInspectionRequestsByUser(user.getUserId());
                    ArrayList<InspectionStation> stations = InspectionStationDAO.getAllStations();
                    ArrayList<Vehicle> vehicles = VehicleDAO.getAllVehicleByUserID(user.getUserId());

                    request.setAttribute("requests", requests);
                    request.setAttribute("stations", stations);
                    request.setAttribute("vehicles", vehicles);

                    request.setAttribute("requests", InspectionRequestDAO.getInspectionRequestsByUser(user.getUserId()));
                    request.getRequestDispatcher("view/lobby.jsp").forward(request, response);
                } else {
                    out.print("InspectionRequest is null");
//                    response.sendRedirect("registerInspection.jsp?error=registrationFailed");
                }
            }

        } else if (action.equals("confirm")) {
            String ops = request.getParameter("response");
            int requestId = Integer.parseInt(request.getParameter("requestId"));

            try {
                if ("accept".equals(ops)) {
                    InspectionRequestDAO.handleInspectionRequest(requestId, "accept");
                } else if ("reject".equals(ops)) {
                    InspectionRequestDAO.handleInspectionRequest(requestId, "reject");
                }
                request.getRequestDispatcher("view/lobby.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Đã xảy ra lỗi khi xử lý yêu cầu.");
            }
        }
    }
//-------------------------------------------------------------------------------
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = response.getWriter();
//        HttpSession session = request.getSession();
//        String action = request.getParameter("action");
//        User user = (User) request.getSession().getAttribute("user");
//        if (user == null) {
//            response.sendRedirect(request.getContextPath() + "/login.jsp");
//            return; // Nếu không có user, chuyển hướng đến trang đăng nhập
//        }
//        String userId = user.getUserId();
//        if ("markAllAsRead".equals(action)) {
//            // Lấy danh sách thông báo của người dùng
//            ArrayList<Notification> notifications = NotificationDAO.getAllNotificationsByUserId(userId);
//            // Cập nhật tất cả thông báo thành đã đọc
//            for (Notification notification : notifications) {
//                NotificationDAO.changeIsReadToDone(userId);
//            }
//            // Chuyển hướng về lobby.jsp
//            response.sendRedirect(request.getContextPath() + "/index.jsp");
//            return; // Đảm bảo không thực hiện thêm bất kỳ lệnh nào sau đó
//        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
