#include <stdio.h>
#include <stdlib.h> 
#include <math.h>   


void printMenu();
void inputArray(int arr[], int *n);
void outputArray(int arr[], int n);
void sortDescending(int arr[], int n);
void checkAllOdd(int arr[], int n);
void searchValue(int arr[], int n);
int isPrime(int num); // Hàm phụ 
void displayPrimes(int arr[], int n);



int main() {
    
    int arr[100];
    
    int n = 0;
    int choice;

    
    do {
        printMenu();
        printf("Moi chon chuc nang (1-7): ");
        scanf("%d", &choice);

       
        switch (choice) {
            case 1:
                inputArray(arr, &n);
                break;
            case 2:
                
                if (n > 0) {
                    outputArray(arr, n);
                } else {
                    printf("Mang rong. Vui long chon chuc nang 1 de nhap mang.\n");
                }
                break;
            case 3:
                 if (n > 0) {
                    sortDescending(arr, n);
                    printf("Da sap xep mang giam dan. Chon chuc nang 2 de xem ket qua.\n");
                } else {
                    printf("Mang rong. Vui long chon chuc nang 1 de nhap mang.\n");
                }
                break;
            case 4:
                 if (n > 0) {
                    checkAllOdd(arr, n);
                } else {
                    printf("Mang rong. Vui long chon chuc nang 1 de nhap mang.\n");
                }
                break;
            case 5:
                 if (n > 0) {
                    searchValue(arr, n);
                } else {
                    printf("Mang rong. Vui long chon chuc nang 1 de nhap mang.\n");
                }
                break;
            case 6:
                 if (n > 0) {
                    displayPrimes(arr, n);
                } else {
                    printf("Mang rong. Vui long chon chuc nang 1 de nhap mang.\n");
                }
                break;
            case 7: { 
                int confirm;
                printf("Ban co chac chan muon thoat? (Nhap 1 de xac nhan): ");
                scanf("%d", &confirm);
                if (confirm == 1) {
                    printf("Tam biet!\n");
                    exit(0); 
                }
                break;
            }
            default:
                printf("Lua chon khong hop le. Vui long chon lai tu 1 den 7.\n");
        }

        
        printf("\nNhan Enter de tiep tuc...");
        
        while (getchar() != '\n');
        getchar();

    } while (1); 

    return 0;
}



void printMenu() {
    system("cls || clear"); 
    printf("===== MENU QUAN LY MANG SO NGUYEN =====\n");
    printf("1. Nhap mang\n");
    printf("2. Xuat mang\n");
    printf("3. Sap xep mang giam dan\n");
    printf("4. Kiem tra mang toan so le\n");
    printf("5. Tim kiem mot gia tri\n");
    printf("6. Hien thi cac so nguyen to trong mang\n");
    printf("7. Thoat\n");
    printf("========================================\n");
}

// Chức năng 1: Nhập mảng
void inputArray(int arr[], int *n) {
    
    do {
        printf("Nhap so luong phan tu (1-100): ");
        scanf("%d", n); 
        if (*n <= 0 || *n > 100) {
            printf("So luong khong hop le. Vui long nhap lai.\n");
        }
    } while (*n <= 0 || *n > 100);

    printf("Moi ban nhap %d phan tu:\n", *n);
    
    for (int i = 0; i < *n; i++) {
        printf("arr[%d] = ", i);
        scanf("%d", &arr[i]);
    }
    printf("Da nhap mang thanh cong!\n");
}

// Chức năng 2: Xuất mảng
void outputArray(int arr[], int n) {
    printf("Cac phan tu trong mang la: ");
   
    for (int i = 0; i < n; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");
}

// Chức năng 3: Sắp xếp giảm dần (Sử dụng thuật toán Bubble Sort)
void sortDescending(int arr[], int n) {
    
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            
            if (arr[j] < arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}

// Chức năng 4: Kiểm tra tất cả phần tử là số lẻ
void checkAllOdd(int arr[], int n) {
    int allOddFlag = 1; 
    for (int i = 0; i < n; i++) {
        
        if (arr[i] % 2 == 0) { 
            allOddFlag = 0; 
            break;           
        }
    }

    if (allOddFlag == 1) {
        printf("DUNG. Tat ca cac phan tu trong mang deu la so le.\n");
    } else {
        printf("SAI. Mang co chua it nhat mot so chan.\n");
    }
}

// Chức năng 5: Tìm kiếm giá trị và đếm số lần xuất hiện
void searchValue(int arr[], int n) {
    int x;
    int count = 0;
    printf("Nhap gia tri can tim (x): ");
    scanf("%d", &x);

    for (int i = 0; i < n; i++) {
        if (arr[i] == x) {
            count++; 
        }
    }
    printf("Gia tri %d xuat hien %d lan trong mang.\n", x, count);
}

// Hàm phụ: Kiểm tra một số có phải là số nguyên tố không
int isPrime(int num) {
    if (num < 2) {
        return 0; 
    }
    
    for (int i = 2; i <= sqrt(num); i++) {
        if (num % i == 0) {
            return 0; 
        }
    }
    return 1; 
}

// Chức năng 6: Hiển thị các số nguyên tố
void displayPrimes(int arr[], int n) {
    int foundFlag = 0;
    printf("Cac so nguyen to trong mang la: ");
    for (int i = 0; i < n; i++) {
        
        if (isPrime(arr[i])) {
            printf("%d ", arr[i]);
            foundFlag = 1; 
        }
    }

    if (foundFlag == 0) {
        printf("Khong co so nguyen to nao.");
    }
    printf("\n");
}
