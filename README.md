### Kiểm thử tính độc lập (Fault Tolerance Test
Trong kiến trúc Microservices, mỗi service được triển khai độc lập, có database riêng và chạy trên port riêng. Khi Product Service dừng hoạt động, Customer Service vẫn xử lý các yêu cầu bình thường vì không phụ thuộc trực tiếp vào Product Service hoặc cơ sở dữ liệu của nó.

Điều này khác với kiến trúc Monolith, nơi toàn bộ chức năng nằm trong một ứng dụng. Nếu ứng dụng Monolith gặp sự cố hoặc dừng hoạt động, tất cả các chức năng (Customer, Product, Order...) đều ngừng theo.

Kết quả kiểm thử cho thấy Microservices có khả năng chịu lỗi (Fault Tolerance) tốt hơn và giúp cô lập sự cố giữa các thành phần của hệ thống. Đây cũng là một trong những ưu điểm quan trọng của kiến trúc Microservices.
