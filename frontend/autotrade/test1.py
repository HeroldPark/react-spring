import pyupbit

access = "PRxRGT50Add4NHtzmUkLXIh8HM450T6kwggM..."
secret = "ghhEXwWUWjOwCKNhBSiepYPAxaAtacVipUDU..."
upbit = pyupbit.Upbit(access, secret)

# 업비트 > 총 보유자산 에서
print(upbit.get_balance("KRW-BTC")) # KRW-BTC(비트코인) 조회
print(upbit.get_balance("KRW-XRP")) # KRW-XRP(리플) 조회
print(upbit.get_balance("KRW"))     # 보유 현금(원화) 조회