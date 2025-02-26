package util;

    import dto.PlaceOrder;
    import dto.ProductOrder;
    import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.FileOutputStream;
import java.util.List;

    public class InvoiceGenerator {

        public static void generateInvoice(String filePath, PlaceOrder placeOrder, List<ProductOrder> orders) {
            Document document = new Document();
            try {
                PdfWriter.getInstance(document, new FileOutputStream(filePath));
                document.open();

                // Title
                Font titleFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD);
                Paragraph title = new Paragraph("Clothify Store", titleFont);
                title.setAlignment(Element.ALIGN_CENTER);
                document.add(title);
                document.add(new Paragraph("\n"));

                // Order Details
                Font orderDetailsFont = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
                document.add(new Paragraph("Order ID     : " + placeOrder.getOrderID(), orderDetailsFont));
                document.add(new Paragraph("User ID      : " + placeOrder.getUserID(), orderDetailsFont));
                document.add(new Paragraph("Order Date: " + placeOrder.getDate(), orderDetailsFont));
                document.add(new Paragraph("Total          : " + placeOrder.getTotal(), orderDetailsFont));
                document.add(new Paragraph("\n"));

                // Table for order items
                PdfPTable table = new PdfPTable(2); // 2 columns
                table.setWidthPercentage(100);
                table.setSpacingBefore(10f);
                table.setSpacingAfter(10f);

                // Table Headers
                table.addCell(getCell("Product Name", Font.BOLD));
                table.addCell(getCell("Quantity", Font.BOLD));

                // Table Data
                for (ProductOrder order : orders) {
                    table.addCell(getCell(order.getProductName(), Font.NORMAL));
                    table.addCell(getCell(String.valueOf(order.getQty()), Font.NORMAL));
                }

                document.add(table);
                document.close();
                System.out.println("Invoice generated successfully: " + filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private static PdfPCell getCell(String text, int fontStyle) {
            Font font = new Font(Font.FontFamily.HELVETICA, 12, fontStyle);
            PdfPCell cell = new PdfPCell(new Phrase(text, font));
            cell.setPadding(5);
            return cell;
        }
    }

