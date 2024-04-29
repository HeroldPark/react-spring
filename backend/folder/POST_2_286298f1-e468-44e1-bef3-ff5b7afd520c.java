public class TCPSlaveTest {

    private final static Logger logger = LoggerFactory.getLogger(TCPSlaveTest.class);
 
     public static void main(String[] args) {
 
        System.out.println("jamod Modbus Slave (Server)");

        ModbusTCPListener listener = null;
        SimpleProcessImage spi = null;
        String ip = "127.0.0.1";
        int port = Modbus.DEFAULT_PORT;
        int unitId = 15;
 
         try {
            // 1. prepare a process image
            spi = new SimpleProcessImage();
            spi.addRegister(new SimpleRegister(0)); // Add a register to hold the received value(initialize to 0)
 
            // 2. create the coupler holding the image
            ModbusCoupler.getReference().setProcessImage(spi);
            ModbusCoupler.getReference().setMaster(false);
            ModbusCoupler.getReference().setUnitID(unitId);
 
            // 3. create a listener with 3 threads in pool
            logger.debug("Listening...");
            
            listener = new ModbusTCPListener(3);   // 3 threads for 3 ports
            InetAddress address = InetAddress.getByName(ip);
            listener.setAddress(address);
            listener.setPort(port);
            logger.debug("Listening to " + ip + " on port " + port);

            listener.start();
            logger.debug("Received...");
			
			// Print received data
            int rCount = spi.getRegisterCount();
            for (int i = 0; i < rCount; i++) {
                Register reg = spi.getRegister(i);
                System.out.println("reg[" + i + "]=" + reg.getValue());
            }

            logger.debug("Exiting...");
            listener.stop();
 
         } catch (Exception ex) {
            ex.printStackTrace();
         }
     }
}